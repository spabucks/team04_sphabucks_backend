package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.event.model.Event;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.repository.IEventRepository;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.productimage.service.IProductImageService;
import sphabucks.products.model.*;
import sphabucks.products.repository.*;
import sphabucks.products.vo.*;
import sphabucks.tag.repository.IProductTagRepository;

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

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    @Override
    public List<ResponseProduct> getBestBigCategory(Long bigCategoryId) {

        if(bigCategoryId != 0 && iProductCategoryListRepository.findAllByBigCategoryId(bigCategoryId).isEmpty()){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }

        List<ProductCategoryList> productCategoryLists;
        if (bigCategoryId == 0) {
            productCategoryLists = iProductCategoryListRepository.findAll();
        } else {
            productCategoryLists = iProductCategoryListRepository.findAllByBigCategoryId(bigCategoryId);
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
                    .build());
        });

        return responseProductList;
    }


    // 상품 검색 기능
    public List<ResponseSearchProduct> searchProductKeyword(String keyword, Pageable pageable) {

        if(iProductRepository.findByNameContains(keyword, pageable).isEmpty()){
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode());
        }
        Page<Product> productList = iProductRepository.findByNameContains(keyword, pageable);

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
                    //.imgUrl(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
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

    // 상품 검색시 상단 메뉴 호출 (키워드 검색)
    @Override
    public ResponseSearchMenu searchProductKeywordMenu(String keyword, Pageable pageable) {
        List<ResponseSearchProduct> responseSearchProductList = searchProductKeyword(keyword, pageable);

        List<ResponseMenu> responseMenu_size = new ArrayList<>();
        List<String> size = new ArrayList<>();
        size.add("Short");
        size.add("Tall");
        size.add("Grande");
        size.add("Venti");
        for (String item : size) {
            ResponseMenu responseMenu4 = ResponseMenu.builder()
                    .name(item)
                    .build();
            responseMenu_size.add(responseMenu4);
        }

        List<ResponseMenu> responseMenu_price = new ArrayList<>();
        List<String> price = new ArrayList<>();
        price.add("1만원미만");
        price.add("1만원대");
        price.add("2만원대");
        price.add("3만원대");
        price.add("4만원대");
        price.add("5만원이상");
        for (String item : price) {
            ResponseMenu responseMenu5 = ResponseMenu.builder()
                    .name(item)
                    .build();
            responseMenu_price.add(responseMenu5);
        }

        List<Map<String, String>> responseMenu_bigCategory = new ArrayList<>();
        Map<String, String>  Map = new HashMap<>();
        Map.put("index", "0");
        Map.put("name", "전체");
        responseMenu_bigCategory.add(Map);
        List<Map<String, String>> responseMenu_smallCategory = new ArrayList<>();


        for (int i = 0; i < responseSearchProductList.size(); i++) {
            Map<String, String> tmpMap = new HashMap<>();
            tmpMap.put("index", Long.toString(
                    iProductCategoryListRepository.findAllByProductId(responseSearchProductList.get(i).getProductId()).get(0).getBigCategory().getId()
            ));
            tmpMap.put("name", responseSearchProductList.get(i).getBigCategory());
            responseMenu_bigCategory.add(tmpMap);

            Map<String, String> tmpMap2 = new HashMap<>();
            tmpMap2.put("bigCategory", responseSearchProductList.get(i).getBigCategory());
            tmpMap2.put("name", responseSearchProductList.get(i).getSmallCategory());

            responseMenu_smallCategory.add(tmpMap2);
        }

        HashSet<Map<String, String>> tmpSet1 = new HashSet<>(responseMenu_bigCategory);
        responseMenu_bigCategory = new ArrayList<>(tmpSet1);
        responseMenu_bigCategory.sort((o1, o2) -> Integer.parseInt(o1.get("index")) - Integer.parseInt(o2.get("index")));

        HashSet<Map<String, String>> tmpSet2 = new HashSet<>(responseMenu_smallCategory);
        responseMenu_smallCategory = new ArrayList<>(tmpSet2);


        List<ResponseMenu> responseMenu_season = new ArrayList<>();
        List<Event> eventList = iEventRepository.findAll();
        for (Event event : eventList) {
            if (!event.getSeason().equals("일반")) {
                ResponseMenu responseMenu3 = ResponseMenu.builder()
                        .name(event.getSeason())
                        .build();
                responseMenu_season.add(responseMenu3);
            }
        }

        for (Map<String, String> map : responseMenu_bigCategory) {
            int num = 0;
            if (!map.get("name").equals("전체")) {
                for (ResponseSearchProduct item : responseSearchProductList) {
                    if (item.getBigCategory().equals(map.get("name"))) {
                        num++;
                    }
                }
                map.put("name", map.get("name")+"("+num+")") ;
            }

        }

        return ResponseSearchMenu.builder()
                .bigCategory(responseMenu_bigCategory)
                .size(responseMenu_size)
                .price(responseMenu_price)
                .smallCategory(responseMenu_smallCategory)
                .season(responseMenu_season)
                .build();
    }

    // 빅카테고리 조회 (햄버거 메뉴에서 넘어갈 떄 사용)
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

    // 빅카테고리별 서브 카테고리 조회 (햄버거 메뉴때 사용)
    @Override
    public List<ResponseCategoryMenu> getAllSubCategory(Long bigCategoryId) {

        if ( bigCategoryId != 0 && iBigCategoryRepository.findById(bigCategoryId).isEmpty() ) {
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }
        // 리턴할 결과 리스트
        List<ResponseCategoryMenu> result = new ArrayList<>();

        // 사이즈 (텀블러, 머그만)
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
                    .data(listSize)
                    .build();
            result.add(responseCategorySmallCategory);
        }

        // 가격 (공통)
        List<ResponseMenu> listPrice = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0 : ResponseMenu responsePrice1 = ResponseMenu.builder()
                        .index(Integer.toUnsignedLong(i+1))
                        .id(0L)
                        .name("1만원 미만")
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
                        .name(i + "만원 이상")
                        .build();
                    listPrice.add(responsePrice3);
                    break;
            }
        }

        // 항목별로 가격 index 다르게 설정
        Long indexPrice = 1L;
        if (bigCategoryId == 2 || bigCategoryId == 3) {
            indexPrice = 2L;
        }
        ResponseCategoryMenu responseCategoryPrice = ResponseCategoryMenu.builder()
                .index(indexPrice)
                .title("가격")
                .data(listPrice)
                .build();
        result.add(responseCategoryPrice);

        // big 카테고리별로 small 카테고리
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
                    .data(listSmallCategory)
                    .build();
            result.add(responseCategorySmallCategory);
        }

        // 시즌 (공통) (list 사이즈별로 다르게 처리)
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
                .data(listSeason)
                .build();
        result.add(responseCategorySeason);

        return result;
    }

//    @Override
//    public List<ResponseSearchResult> testSearch(RequestSearchParam requestSearchParam) {
//        return productRepository.searchProduct(requestSearchParam);
//    }

    @Override
    public List<ResponseSearchResult> searchProduct(RequestSearchParam requestSearchParam, Pageable pageable) {

        List<ProductSearch> productSearchList = productRepository.searchProduct(requestSearchParam, pageable);

        List<ResponseSearchResult> responseSearchResultList = new ArrayList<>();

        log.info("{}", productSearchList);

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
