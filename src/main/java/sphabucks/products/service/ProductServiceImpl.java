package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.event.model.Event;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.repository.IEventRepository;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.productimage.service.IProductImageService;
import sphabucks.products.model.Product;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.IBigCategoryRepository;
import sphabucks.products.repository.IProductCategoryListRepository;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.vo.*;
import sphabucks.tag.repository.IProductTagRepository;
import sphabucks.users.service.IUserService;

import java.sql.Array;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;
    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IEventProductListRepository iEventProductListRepository;
    private final IProductTagRepository iProductTagRepository;
    private final IEventRepository iEventRepository;
    private final IProductImageService iProductImageService;

    @Override
    public void addProduct(RequestProduct requestProduct) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);

        iProductRepository.save(product);
    }

    @Override
    public ResponseProduct getProduct(Long id) {
        Product product = iProductRepository.findById(id).get();
        List<ProductImage> productImages = iProductImageRepo.findAllByProductId(id);
        String thumbnail = productImages.get(0).getImage();
        List<String> detailImages = new ArrayList<>();
        for (int i=1; i<productImages.size(); i++) detailImages.add(productImages.get(i).getImage());
        return ResponseProduct.builder()
                .id(id)
                .title(product.getName())
                .imgUrl(thumbnail)
                .description(product.getDescription())
                .price(product.getPrice())
                .productDetailImgUrl(detailImages)
                .build();
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    @Override
    public List<ResponseProduct> getBestBigCategory(Integer bigCategoryId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findAllByBigCategoryId(bigCategoryId);
        List<ResponseProduct> responseProductList = new ArrayList<>();

        productCategoryLists.forEach(productList -> {

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
                    .build());
        });

        return responseProductList;
    }

    // 상품 검색 기능
    public List<ResponseSearchProduct> searchProductKeyword(String keyword) {

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
                    .event(iEventProductListRepository.findByProductId(product.getId()).getEvent().getSeason())
                    .tag(tag)
                    .productName(product.getName())
                    //.imgUrl(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
                    .imgUrl(iProductImageService.getProductImage(product.getId()).get(0).getImage())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .amount(product.getAmount())
                    .isBest(product.getIsBest())
                    .isNew(product.getIsNew())
                    .build();

            responseSearchProductList.add(responseProduct);
        });

        return responseSearchProductList;
    }

    // 상품 검색시 상단 메뉴 호출 (키워드 검색)
    @Override
    public ResponseSearchMenu searchProductKeywordMenu(String keyword) {
        List<ResponseSearchProduct> responseSearchProductList = searchProductKeyword(keyword);

        List<ResponseMenu> responseMenu_size = new ArrayList<>();
        List<String> size = new ArrayList<>();
        size.add("Short");
        size.add("Tall");
        size.add("Grande");
        size.add("Venti");
        for (int i = 0; i < size.size(); i++) {
            ResponseMenu responseMenu4 = ResponseMenu.builder()
                    .name(size.get(i))
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
        for (int i = 0; i < price.size(); i++) {
            ResponseMenu responseMenu5 = ResponseMenu.builder()
                    .name(price.get(i))
                    .build();
            responseMenu_price.add(responseMenu5);
        }

        List<ResponseMenu> responseMenu_bigCategory = new ArrayList<>();
        List<ResponseMenu> responseMenu_smallCategory = new ArrayList<>();

        for (int i = 0; i < responseSearchProductList.size(); i++) {
            ResponseMenu responseMenu1 = ResponseMenu.builder()
                    .name(responseSearchProductList.get(i).getBigCategory())
                    .build();
            responseMenu_bigCategory.add(responseMenu1);

            ResponseMenu responseMenu2 = ResponseMenu.builder()
                    .name(responseSearchProductList.get(i).getSmallCategory())
                    .build();
            responseMenu_smallCategory.add(responseMenu2);
        }

        HashSet tmpSet1 = new HashSet(responseMenu_bigCategory);
        List tmpList1 = new ArrayList<>(tmpSet1);
        responseMenu_bigCategory = tmpList1;

        HashSet tmpSet2 = new HashSet(responseMenu_smallCategory);
        List tmpList2 = new ArrayList<>(tmpSet2);
        responseMenu_smallCategory = tmpList2;


        List<ResponseMenu> responseMenu_season = new ArrayList<>();
        List<Event> eventList = iEventRepository.findAll();
        for (int i = 0; i < eventList.size(); i++) {
            if (!eventList.get(i).getSeason().equals("일반")) {
                ResponseMenu responseMenu3 = ResponseMenu.builder()
                        .name(eventList.get(i).getSeason())
                        .build();
                responseMenu_season.add(responseMenu3);
            }
        }


        ResponseSearchMenu responseSearchMenu = ResponseSearchMenu.builder()
                .bigCategory(responseMenu_bigCategory)
                .size(responseMenu_size)
                .price(responseMenu_price)
                .smallCategory(responseMenu_smallCategory)
                .season(responseMenu_season)
                .build();



        return responseSearchMenu;
    }


}
