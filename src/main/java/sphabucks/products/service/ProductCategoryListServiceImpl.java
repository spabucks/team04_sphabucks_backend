package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.event.model.EventProductList;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.repository.IEventRepository;
import sphabucks.event.service.EventProductServiceImpl;
import sphabucks.event.service.IEventProductService;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.*;
import sphabucks.products.vo.RequestProductCategoryList;
import sphabucks.products.vo.ResponseProductList;
import sphabucks.products.vo.ResponseProductSummary;

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

        ProductCategoryList productCategoryList = ProductCategoryList.builder()
                .smallCategory(iSmallCategoryRepository.findById(requestProductCategoryList.getSmallCategoryId()).get())
                .bigCategory(iBigCategoryRepository.findById(requestProductCategoryList.getBigCategoryId()).get())
                .product(iProductRepository.findById(requestProductCategoryList.getProductId()).get())
                .build();

        iProductCategoryListRepository.save(productCategoryList);

    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {
        return iProductCategoryListRepository.findAllByProductId(productId);
    }

    @Override
    public List<ResponseProductSummary> getProductsBySmallCategoryId(Integer smallCategoryId) {
        List<ResponseProductSummary> responseProductSummaries = new ArrayList<>();
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findAllBySmallCategoryId(smallCategoryId);
        for (ProductCategoryList productCategoryList : productCategoryLists) {
            Product product = iProductRepository.findById(productCategoryList.getProduct().getId()).get();
            responseProductSummaries.add(ResponseProductSummary.builder()
                            .id(product.getId())
                            .title(product.getName())
                            .price(product.getPrice())
                            .isNew(product.getIsNew())
                            .imgUrl(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
                            .build());
        }
        return responseProductSummaries;
    }

    @Override
    public List<ResponseProductList> getOtherProductByProductId(Long productId) {
        List<ResponseProductList> return_value = new ArrayList<>();
        // 1. 현재 보고있는 상품과 동일한 이벤트 상품들을 검색
        // 2. 현재 보고있는 상품과 동일한 소분류 카테고리 상품들을 검색

        Long currentEventId = iEventProductListRepository.findByProductId(productId).getEvent().getId();
        return_value.add(ResponseProductList.builder()
                .id(1L)
                .name(iEventRepository.findById(currentEventId).get().getSeason())
                .data(iEventProductService.getProductsByEventId(currentEventId))
                .build());

        Integer currentSmallCategoryId = iProductCategoryListRepository.findById(productId).get().getSmallCategory().getId();
        return_value.add(ResponseProductList.builder()
                .id(2L)
                .name("다른 고객이 함께 본 상품")
                .data(getProductsBySmallCategoryId(currentSmallCategoryId))
                .build());
        return return_value;
    }
}
