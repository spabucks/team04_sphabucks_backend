package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.IProductCategoryListRepository;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.vo.RequestProduct;
import sphabucks.products.vo.ResponseProduct;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;
    private final IProductCategoryListRepository iProductCategoryListRepository;

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
    public List<ResponseProduct> searchProductKeyword(String keyword) {

        List<Product> productList = iProductRepository.findByNameContains(keyword);

        List<ResponseProduct> responseProductList = new ArrayList<>();

        productList.forEach(product -> {
            ResponseProduct responseProduct = ResponseProduct.builder()
                    .id(product.getId())
                    .title(product.getName())
                    .imgUrl(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();

            responseProductList.add(responseProduct);
        });

        return responseProductList;
    }


}
