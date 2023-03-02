package sphabucks.tag.service;

import sphabucks.tag.model.ProductTag;
import sphabucks.products.model.Product;
import sphabucks.tag.model.Tag;
import sphabucks.tag.vo.RequsetProductTag;
import sphabucks.tag.vo.ResponseProductTag;

import java.util.List;


public interface IProductTagService {
    void addProductTag(RequsetProductTag requsetProductTag);
    List<ProductTag> getByProductId(Long productId);


    ResponseProductTag getByTagId(Long tagId);
}
