package sphabucks.tag.service;

import sphabucks.tag.model.ProductTag;
import sphabucks.products.model.Product;
import sphabucks.tag.model.Tag;
import sphabucks.tag.vo.RequsetProductTag;

import java.util.List;


public interface IProductTagService {

    ProductTag addProductTag(RequsetProductTag requsetProductTag);
    List<ProductTag> getByProductId(Long productId);
}
