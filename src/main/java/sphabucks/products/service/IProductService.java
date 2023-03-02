package sphabucks.products.service;

import sphabucks.products.model.Product;
import sphabucks.products.vo.RequestProduct;
import sphabucks.products.vo.ResponseProduct;

import java.util.List;

public interface IProductService {
    void addProduct(RequestProduct requestProduct);
    ResponseProduct getProduct(Long productId);
    List<Product> getAll();
}
