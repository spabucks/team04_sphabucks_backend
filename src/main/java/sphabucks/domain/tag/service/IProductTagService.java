package sphabucks.domain.tag.service;

import sphabucks.domain.tag.vo.RequestProductTag;
import sphabucks.domain.tag.vo.ResponseProductTag;
import sphabucks.domain.tag.model.ProductTag;

import java.util.List;


public interface IProductTagService {
    void addProductTag(RequestProductTag requestProductTag);
    List<ProductTag> getProductId(Long productId);
    List<ResponseProductTag> getAll();

    ResponseProductTag getTagId(Long tagId);
}
