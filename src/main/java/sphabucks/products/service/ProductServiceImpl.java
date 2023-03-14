package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sphabucks.event.model.Event;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.repository.IEventRepository;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.productimage.service.IProductImageService;
import sphabucks.products.model.Product;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.IProductCategoryListRepository;
import sphabucks.products.repository.IProductRepository;
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
                .likeCount(product.getLikeCount())
                .build();
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    @Override
    public List<ResponseProduct> getBestBigCategory(Long bigCategoryId) {
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
                    .likeCount(productList.getProduct().getLikeCount())
                    .build());
        });

        return responseProductList;
    }


    // 상품 검색 기능
    public List<ResponseSearchProduct> searchProductKeyword(String keyword, Pageable pageable) {

        Page<Product> productList = iProductRepository.findByNameContains(keyword, pageable);

        List<ResponseSearchProduct> responseSearchProductList = new ArrayList<>();


        // paging 처리 할 때 page별로 foreach 문 사용되어 있는데,
        // List별로 foreach 문 사용 후 paging 처리를 해줘야 할 것 같다.
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


}
