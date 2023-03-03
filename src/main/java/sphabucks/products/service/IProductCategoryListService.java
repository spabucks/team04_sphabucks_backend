package sphabucks.products.service;

import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.vo.RequestProductCategoryList;
import sphabucks.products.vo.ResponseOtherProducts;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestProductCategoryList requestProductCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);

    List<ResponseOtherProducts> getOtherProductByProductId(Long productId); // 다른 고객이 함께 본 상품 api(소분류 카테고리 검색)

}
