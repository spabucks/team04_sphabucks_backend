package sphabucks.domain.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.domain.event.repository.IEventProductListRepository;
import sphabucks.domain.event.repository.IEventRepository;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.products.repository.IBigCategoryRepository;
import sphabucks.domain.products.repository.IProductCategoryListRepository;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.products.repository.ISmallCategoryRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.event.service.IEventProductService;
import sphabucks.domain.productimage.repository.IProductImageRepo;
import sphabucks.domain.products.model.ProductCategoryList;
import sphabucks.domain.products.vo.RequestProductCategoryList;
import sphabucks.domain.products.vo.ResponseProductList;
import sphabucks.domain.products.vo.ResponseProductSummary;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements IProductCategoryListService{

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final ISmallCategoryRepository iSmallCategoryRepository;
    private final IBigCategoryRepository iBigCategoryRepository;
    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;
    private final IEventProductListRepository iEventProductListRepository;
    private final IEventRepository iEventRepository;
    private final IEventProductService iEventProductService;

    @Override
    public void addProductCategoryList(RequestProductCategoryList requestProductCategoryList) {

        if(!iProductCategoryListRepository.findAllByProductId(requestProductCategoryList.getProductId()).isEmpty()){
            throw new BusinessException(ErrorCode.DUPLICATE_PRODUCT, ErrorCode.DUPLICATE_PRODUCT.getCode());
        }

        ProductCategoryList productCategoryList = ProductCategoryList.builder()
                .smallCategory(iSmallCategoryRepository.findById(requestProductCategoryList.getSmallCategoryId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode())))
                .bigCategory(iBigCategoryRepository.findById(requestProductCategoryList.getBigCategoryId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode())))
                .product(iProductRepository.findById(requestProductCategoryList.getProductId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode())))
                .build();

        iProductCategoryListRepository.save(productCategoryList);

    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {

        if(iProductCategoryListRepository.findAllByProductId(productId).isEmpty()){
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode());
        }

        return iProductCategoryListRepository.findAllByProductId(productId);
    }

    @Override
    public List<ResponseProductSummary> getProductsBySmallCategoryId(Long smallCategoryId) {
        List<ResponseProductSummary> responseProductSummaries = new ArrayList<>();
        if(iProductCategoryListRepository.findAllBySmallCategoryId(smallCategoryId).isEmpty()){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findAllBySmallCategoryId(smallCategoryId);
        for (ProductCategoryList productCategoryList : productCategoryLists) {
            Product product = iProductRepository.findById(productCategoryList.getProduct().getId())
                    .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
            responseProductSummaries.add(ResponseProductSummary.builder()
                    .id(product.getId())
                    .title(product.getName())
                    .price(product.getPrice())
                    .isNew(product.getIsNew())
                    .imgUrl(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
                    .isBest(product.getIsBest())
                    .build());
        }
        return responseProductSummaries;
    }

    @Override
    public List<ResponseProductList> getOtherProductByProductId(Long productId) {
        List<ResponseProductList> return_value = new ArrayList<>();
        // 1. 현재 보고있는 상품과 동일한 이벤트 상품들을 검색
        // 2. 현재 보고있는 상품과 동일한 소분류 카테고리 상품들을 검색

        Long currentEventId = iEventProductListRepository.findByProductId(productId)
                .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()))
                .getEvent().getId();

        if(iEventProductService.getProductsByEventId(currentEventId).isEmpty()){
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode());
        }
        return_value.add(ResponseProductList.builder()
                .id(1L)
                .name(iEventRepository.findById(currentEventId)
                        .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_EXISTS, ErrorCode.EVENT_NOT_EXISTS.getCode()))
                        .getSeason())
                .description(iEventRepository.findById(currentEventId)
                        .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_EXISTS, ErrorCode.EVENT_NOT_EXISTS.getCode()))
                        .getDescription())
                .data(iEventProductService.getProductsByEventId(currentEventId))
                .build());

        Long currentSmallCategoryId = iProductCategoryListRepository.findById(productId)
                .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()))
                .getSmallCategory().getId();
        return_value.add(ResponseProductList.builder()
                .id(2L)
                .name("다른 고객이 함께 본 상품")
                .description("다른 고객이 함께 본 상품")
                .data(getProductsBySmallCategoryId(currentSmallCategoryId))
                .build());
        return return_value;
    }
}
