package sphabucks.productimage.service;

import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.vo.RequestProductImage;
import sphabucks.products.model.Product;

import java.util.List;

public interface IProductImageService {

    void addProductImage(RequestProductImage requestProductImage);

    List<ProductImage> getProductImage(Long id);
    List<ProductImage> getAll();
}
