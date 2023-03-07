package sphabucks.tag.service;

import sphabucks.tag.model.ProductTag;
import sphabucks.tag.vo.RequestProductTag;
import sphabucks.tag.vo.ResponseProductTag;

import java.util.List;


public interface IProductTagService {
    void addProductTag(RequestProductTag requestProductTag);
    List<ProductTag> getProductId(Long productId);
//    List<ResponseProductTag> getAllByTagId();
    List<ResponseProductTag> getAll();
}
