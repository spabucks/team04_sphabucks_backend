package sphabucks.products.service;

import sphabucks.products.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product getProduct(Long productId);
    List<Product> getAll();
}
