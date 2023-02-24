package sphabucks.product_tag_list.service;

import sphabucks.product_tag_list.model.ProductTag;
import sphabucks.products.model.Product;
import sphabucks.tag.model.Tag;


public interface IProductTagService {

    ProductTag addProductTag(ProductTag productTag, Product product, Tag tag);
    ProductTag getProductTag(Long id);
}
