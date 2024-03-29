package sphabucks.domain.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sphabucks.domain.event.model.Event;
import sphabucks.domain.event.repository.IEventProductListRepository;
import sphabucks.domain.event.repository.IEventRepository;
import sphabucks.domain.products.model.*;
import sphabucks.domain.products.repository.*;
import sphabucks.domain.products.vo.*;
import sphabucks.domain.tag.repository.IProductTagRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.productimage.model.ProductImage;
import sphabucks.domain.productimage.repository.IProductImageRepo;
import sphabucks.domain.productimage.service.IProductImageService;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;
    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IEventProductListRepository iEventProductListRepository;
    private final IProductTagRepository iProductTagRepository;
    private final IEventRepository iEventRepository;
    private final IProductImageService iProductImageService;
    private final IBigCategoryRepository iBigCategoryRepository;
    private final ISmallCategoryRepository iSmallCategoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void addProduct(RequestProduct requestProduct) {

        if(iProductRepository.findByName(requestProduct.getName()).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_PRODUCT, ErrorCode.DUPLICATE_PRODUCT.getCode());
        }

        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);

        iProductRepository.save(product);
    }

    @Override
    public ResponseProduct getProduct(Long id) {

        Product product = iProductRepository.findById(id)
                .orElseThrow(()->new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));

        if(iProductImageRepo.findAllByProductId(id).isEmpty()){
            throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS, ErrorCode.IMAGE_NOT_EXISTS.getCode());
        }

        List<ProductImage> productImages = iProductImageRepo.findAllByProductId(id);
        String thumbnail = productImages.get(0).getImage();
        List<String> detailImages = new ArrayList<>();
        for (int i=1; i<productImages.size(); i++) {
            detailImages.add(productImages.get(i).getImage());
        }

        return ResponseProduct.builder()
                .id(id)
                .title(product.getName())
                .imgUrl(thumbnail)
                .description(product.getDescription())
                .price(product.getPrice())
                .productDetailImgUrl(detailImages)
                .likeCount(product.getLikeCount())
                .build();
    }

    @Override
    public List<Product> getAll() {

        if(iProductRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode());
        }

        return iProductRepository.findAll();
    }

    @Override
    public List<ResponseSearchProduct> getAllProducts(Pageable pageable){

        Page<Product> productList = iProductRepository.findAll(pageable);

        List<ResponseSearchProduct> responseSearchProductList = new ArrayList<>();

        productList.forEach(product -> {
            String tag = "";
            if (iProductTagRepository.findAllByProductId(product.getId()).size() != 0) {
                tag = iProductTagRepository.findAllByProductId(product.getId()).get(0).getTag().getName();
            }
            ResponseSearchProduct responseProduct = ResponseSearchProduct.builder()
                    .productId(product.getId())
                    .bigCategory(iProductCategoryListRepository.findAllByProductId(product.getId()).get(0).getBigCategory().getName())
                    .smallCategory(iProductCategoryListRepository.findAllByProductId(product.getId()).get(0).getSmallCategory().getName())
                    .event(iEventProductListRepository.findByProductId(product.getId())
                            .orElseThrow(()-> new BusinessException(ErrorCode.EVENT_NOT_EXISTS, ErrorCode.EVENT_NOT_EXISTS.getCode()))
                            .getEvent().getSeason())
                    .tag(tag)
                    .productName(product.getName())
                    .imgUrl(iProductImageService.getProductImage(product.getId()).get(0).getImage())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .amount(product.getAmount())
                    .isBest(product.getIsBest())
                    .isNew(product.getIsNew())
                    .likeCount(product.getLikeCount())
                    .build();

            responseSearchProductList.add(responseProduct);
        });

        return responseSearchProductList;
    }

    @Override
    public List<ResponseProduct> getBestBigCategory(Long bigCategoryId) {

        if(bigCategoryId != 0 && iProductCategoryListRepository.findTop30ByBigCategoryIdOrderByProductLikeCountDesc(bigCategoryId).isEmpty()){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }

        List<ProductCategoryList> productCategoryLists;
        if (bigCategoryId == 0) {
            productCategoryLists = iProductCategoryListRepository.findAll();
        } else {
            productCategoryLists = iProductCategoryListRepository.findTop30ByBigCategoryIdOrderByProductLikeCountDesc(bigCategoryId);
        }


        List<ResponseProduct> responseProductList = new ArrayList<>();

        productCategoryLists.forEach(productList -> {

            if(iProductImageRepo.findAllByProductId(productList.getId()).isEmpty()){
                throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS, ErrorCode.IMAGE_NOT_EXISTS.getCode());
            }

            List<ProductImage> productImageList = iProductImageRepo.findAllByProductId(productList.getId());
            List<String> productDetailImage = new ArrayList<>();
            for (int i = 1; i < productImageList.size(); i++) {
                productDetailImage.add(productImageList.get(i).getImage());
            }
            responseProductList.add(ResponseProduct.builder()
                    .id(productList.getId())
                    .title(productList.getProduct().getName())
                    .imgUrl(productImageList.get(0).getImage())
                    .description(productList.getProduct().getDescription())
                    .price(productList.getProduct().getPrice())
                    .productDetailImgUrl(productDetailImage)
                    .likeCount(productList.getProduct().getLikeCount())
                    .isBest(productList.getProduct().getIsBest())
                    .isNew(productList.getProduct().getIsNew())
                    .build());
        });

        return responseProductList;
    }


    public List<ResponseSearchProduct> searchProductKeyword(String keyword) {

        if(iProductRepository.findByNameContains(keyword).isEmpty()){
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode());
        }
        List<Product> productList = iProductRepository.findByNameContains(keyword);

        List<ResponseSearchProduct> responseSearchProductList = new ArrayList<>();

        productList.forEach(product -> {
            String tag = "";
            if (iProductTagRepository.findAllByProductId(product.getId()).size() != 0) {
                tag = iProductTagRepository.findAllByProductId(product.getId()).get(0).getTag().getName();
            }
            ResponseSearchProduct responseProduct = ResponseSearchProduct.builder()
                    .productId(product.getId())
                    .bigCategory(iProductCategoryListRepository.findAllByProductId(product.getId()).get(0).getBigCategory().getName())
                    .smallCategory(iProductCategoryListRepository.findAllByProductId(product.getId()).get(0).getSmallCategory().getName())
                    .event(iEventProductListRepository.findByProductId(product.getId())
                            .orElseThrow(()-> new BusinessException(ErrorCode.EVENT_NOT_EXISTS, ErrorCode.EVENT_NOT_EXISTS.getCode()))
                            .getEvent().getSeason())
                    .tag(tag)
                    .productName(product.getName())
                    .imgUrl(iProductImageService.getProductImage(product.getId()).get(0).getImage())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .amount(product.getAmount())
                    .isBest(product.getIsBest())
                    .isNew(product.getIsNew())
                    .likeCount(product.getLikeCount())
                    .build();

            responseSearchProductList.add(responseProduct);
        });

        return responseSearchProductList;
    }

    @Override
    public List<ResponseBigCategory> searchProductKeywordMenu(String keyword) {
        List<ResponseSearchProduct> responseSearchProductList = searchProductKeyword(keyword);

        HashSet<String> nameSet = new HashSet<>();

        List<ResponseBigCategory> responseBigCategoryList = new ArrayList<>();
        ResponseBigCategory totalMenu = ResponseBigCategory.builder()
                .index(0L)
                .id(0l)
                .name("전체")
                .build();
        responseBigCategoryList.add(totalMenu);

        for (int i = 0; i < responseSearchProductList.size(); i++) {
            if (nameSet.add(responseSearchProductList.get(i).getBigCategory())) {
                ResponseBigCategory responseBigCategory = ResponseBigCategory.builder()
                        .name(responseSearchProductList.get(i).getBigCategory())
                        .id(iProductCategoryListRepository.findAllByProductId(
                                responseSearchProductList.get(i).getProductId()).get(0).getBigCategory().getId())
                        .build();
                responseBigCategoryList.add(responseBigCategory);
            }
        }

        responseBigCategoryList.sort((o1, o2) -> Integer.parseInt(o1.getId()+"")
                - Integer.parseInt(o2.getId()+""));
        for (int i = 0; i < responseBigCategoryList.size(); i++) {
            responseBigCategoryList.get(i).setIndex(Integer.toUnsignedLong(i));
        }

        // 빅카테고리 상품수
        for (ResponseBigCategory responseBigCategory : responseBigCategoryList) {
            int num = 0;
            if (!responseBigCategory.getName().equals("전체")) {
                for (ResponseSearchProduct item : responseSearchProductList) {
                    if (item.getBigCategory().equals(responseBigCategory.getName())) {
                        num++;
                    }
                }
                responseBigCategory.setName(responseBigCategory.getName() + "(" + num + ")");
            }

        }

        return responseBigCategoryList;
    }

    @Override
    public List<ResponseBigCategory> getAllBigCategory() {

        List<BigCategory> list = iBigCategoryRepository.findAll();
        if(list.isEmpty()){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }

        List<ResponseBigCategory> result = new ArrayList<>();
        ResponseBigCategory totalMenu = ResponseBigCategory.builder()
                .index(0L)
                .id(0l)
                .name("전체")
                .build();
        result.add(totalMenu);


        list.forEach(item -> {
            ResponseBigCategory responseBigCategory = ResponseBigCategory.builder()
                    .index(item.getId())
                    .id(item.getId())
                    .name(item.getName())
                    .build();

            result.add(responseBigCategory);
        });

        return result;
    }

    @Override
    public List<ResponseCategoryMenu> getAllSubCategory(Long bigCategoryId) {

        if ( bigCategoryId != 0 && iBigCategoryRepository.findById(bigCategoryId).isEmpty() ) {
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }
        List<ResponseCategoryMenu> result = new ArrayList<>();

        if (bigCategoryId == 2 || bigCategoryId == 3) {
            List<ResponseMenu> listSize = new ArrayList<>();
            ResponseMenu responseMenu1 = ResponseMenu.builder()
                    .index(1L)
                    .id(1L)
                    .name("Short")
                    .build();
            listSize.add(responseMenu1);
            ResponseMenu responseMenu2 = ResponseMenu.builder()
                    .index(2L)
                    .id(2L)
                    .name("Tall")
                    .build();
            listSize.add(responseMenu2);
            ResponseMenu responseMenu3 = ResponseMenu.builder()
                    .index(3L)
                    .id(3L)
                    .name("Grande")
                    .build();
            listSize.add(responseMenu3);
            ResponseMenu responseMenu4 = ResponseMenu.builder()
                    .index(4L)
                    .id(4L)
                    .name("Venti")
                    .build();
            listSize.add(responseMenu4);

            ResponseCategoryMenu responseCategorySmallCategory = ResponseCategoryMenu.builder()
                    .index(1L)
                    .title("용량")
                    .value("size")
                    .data(listSize)
                    .build();
            result.add(responseCategorySmallCategory);
        }

        List<ResponseMenu> listPrice = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0 : ResponseMenu responsePrice1 = ResponseMenu.builder()
                        .index(Integer.toUnsignedLong(i+1))
                        .id(0L)
                        .name("1만원미만")
                        .build();
                    listPrice.add(responsePrice1);
                    break;
                case 1,2,3,4 : ResponseMenu responsePrice2 = ResponseMenu.builder()
                        .index(Integer.toUnsignedLong(i+1))
                        .id(i*10000L)
                        .name(i + "만원대")
                        .build();
                    listPrice.add(responsePrice2);
                    break;
                case 5 : ResponseMenu responsePrice3 = ResponseMenu.builder()
                        .index(Integer.toUnsignedLong(i+1))
                        .id(i*10000L)
                        .name(i + "만원이상")
                        .build();
                    listPrice.add(responsePrice3);
                    break;
            }
        }

        Long indexPrice = 1L;
        if (bigCategoryId == 2 || bigCategoryId == 3) {
            indexPrice = 2L;
        }
        ResponseCategoryMenu responseCategoryPrice = ResponseCategoryMenu.builder()
                .index(indexPrice)
                .title("가격")
                .value("price")
                .data(listPrice)
                .build();
        result.add(responseCategoryPrice);

        if (bigCategoryId == 1 || bigCategoryId == 2 || bigCategoryId == 3) {
            List<SmallCategory> listSmallCategoryDB = iSmallCategoryRepository.findAllByBigCategoryId(bigCategoryId);
            if (listSmallCategoryDB.isEmpty()) {
                throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
            }

            List<ResponseMenu> listSmallCategory = new ArrayList<>();
            for (int i = 0; i < listSmallCategoryDB.size(); i++) {
                ResponseMenu responseMenu = ResponseMenu.builder()
                        .index(Integer.toUnsignedLong(i+1))
                        .id(listSmallCategoryDB.get(i).getId())
                        .name(listSmallCategoryDB.get(i).getName())
                        .build();
                listSmallCategory.add(responseMenu);
            }

            Long smallCategoryIndex = 2L;
            if (bigCategoryId != 1) { smallCategoryIndex = 3L; }
            ResponseCategoryMenu responseCategorySmallCategory = ResponseCategoryMenu.builder()
                    .index(smallCategoryIndex)
                    .title("카테고리")
                    .value("smallCategory")
                    .data(listSmallCategory)
                    .build();
            result.add(responseCategorySmallCategory);
        }

        List<ResponseMenu> listSeason = new ArrayList<>();

        List<Event> eventList = iEventRepository.findAll();
        if (eventList.isEmpty()) {
            throw new BusinessException(ErrorCode.EVENT_NOT_EXISTS, ErrorCode.EVENT_NOT_EXISTS.getCode());
        }
        for (int i = 0; i < eventList.size(); i++) {
            if (!eventList.get(i).getSeason().equals("일반")) {
                ResponseMenu responseSeason = ResponseMenu.builder()
                        .index(Integer.toUnsignedLong(i))
                        .id(eventList.get(i).getId())
                        .name(eventList.get(i).getSeason())
                        .build();
                listSeason.add(responseSeason);
            }
        }
        ResponseCategoryMenu responseCategorySeason = ResponseCategoryMenu.builder()
                .index( Integer.toUnsignedLong(result.size()+1) )
                .title("시즌")
                .value("season")
                .data(listSeason)
                .build();
        result.add(responseCategorySeason);

        return result;
    }

    @Override
    public List<ResponseSearchResult> searchProduct(RequestSearchParam requestSearchParam, Long page) {

        List<ProductSearch> productSearchList = productRepository.searchProduct(requestSearchParam, page);

        List<ResponseSearchResult> responseSearchResultList = new ArrayList<>();

        productSearchList.forEach(item -> {
            ResponseSearchResult responseSearchResult = ResponseSearchResult.builder()
                    .id(item.getProduct().getId())
                    .name(item.getProduct().getName())
                    .description(item.getProduct().getDescription())
                    .price(item.getProduct().getPrice())
                    .size(item.getProduct().getSize())
                    .amount(item.getProduct().getAmount())
                    .isBest(item.getProduct().getIsBest())
                    .isNew(item.getProduct().getIsNew())
                    .likeCount(item.getProduct().getLikeCount())
                    .imgUrl(iProductImageRepo.findAllByProductId(item.getProduct().getId()).get(0).getImage())
                    .build();

            responseSearchResultList.add(responseSearchResult);
        });



        return responseSearchResultList;
    }


}
