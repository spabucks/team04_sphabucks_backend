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

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    List<ResponseProduct> getBestBigCategory(Long bigCategoryId);

    // 상품 검색 메서드 (키워드 검색)
    List<ResponseSearchProduct> searchProductKeyword(String keyword);

    // 상품 검색 상단 메뉴 호출 (키워드 검색)
    List<ResponseBigCategory> searchProductKeywordMenu(String keyword);

    // 빅 카테고리 메뉴 호출
    List<ResponseBigCategory> getAllBigCategory();

    // 빅카테고리별 서브 카테고리 호출
    List<ResponseCategoryMenu> getAllSubCategory(Long bigCategoryId);

    // 테스트 메서드
//    List<ResponseSearchResult> testSearch(RequestSearchParam requestSearchParam);

    List<ResponseSearchResult> searchProduct(RequestSearchParam requestSearchParam, Long page);
}
