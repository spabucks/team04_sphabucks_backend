package sphabucks.products.service;

import sphabucks.products.model.Product;
import sphabucks.products.vo.RequestProduct;

import java.util.List;

public interface IProductService {
    void addProduct(RequestProduct requestProduct);
    Product getProduct(Long productId);
    List<Product> getAll();
}
