package sphabucks.domain.products.service;

import sphabucks.domain.products.model.ProductCategoryList;
import sphabucks.domain.products.vo.RequestProductCategoryList;
import sphabucks.domain.products.vo.ResponseProductList;
import sphabucks.domain.products.vo.ResponseProductSummary;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestProductCategoryList requestProductCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);
    List<ResponseProductSummary> getProductsBySmallCategoryId(Long smallCategoryId);
    List<ResponseProductList> getOtherProductByProductId(Long productId); // 다른 고객이 함께 본 상품 api(소분류 카테고리, 동일 이벤트 검색)
}
