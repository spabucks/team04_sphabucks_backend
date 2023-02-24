package sphabucks.products.service;

import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.vo.RequestProductCategoryList;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestProductCategoryList requestProductCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);

}
