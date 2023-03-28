package sphabucks.domain.productimage.service;

import sphabucks.domain.productimage.model.ProductImage;
import sphabucks.domain.productimage.vo.RequestProductImage;

import java.util.List;

public interface IProductImageService {

    void addProductImage(RequestProductImage requestProductImage);

    List<ProductImage> getProductImage(Long id);
    List<ProductImage> getAll();
}
