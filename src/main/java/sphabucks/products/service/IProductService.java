package sphabucks.products.service;

import sphabucks.products.model.Product;

public interface IProductService {
    Product addProduct(Product product);
    Product getProduct(Long id);
}
