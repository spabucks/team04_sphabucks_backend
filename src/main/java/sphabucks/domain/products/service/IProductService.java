package sphabucks.domain.products.service;

import org.springframework.data.domain.Pageable;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.products.vo.*;

import java.util.List;

public interface IProductService {
    void addProduct(RequestProduct requestProduct);
    ResponseProduct getProduct(Long productId);
    List<Product> getAll();

    List<ResponseSearchProduct> getAllProducts(Pageable pageable);

    List<ResponseProduct> getBestBigCategory(Long bigCategoryId);

    List<ResponseSearchProduct> searchProductKeyword(String keyword);

    List<ResponseBigCategory> searchProductKeywordMenu(String keyword);

    List<ResponseBigCategory> getAllBigCategory();

    List<ResponseCategoryMenu> getAllSubCategory(Long bigCategoryId);

    List<ResponseSearchResult> searchProduct(RequestSearchParam requestSearchParam, Long page);
}
