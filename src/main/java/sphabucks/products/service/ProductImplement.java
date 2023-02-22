package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;

@RequiredArgsConstructor
@Service
public class ProductImplement implements IProductService{

    private final IProductRepository iProductRepository;
    @Override
    public Product addProduct(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return iProductRepository.findById(id).get();
    }
}
