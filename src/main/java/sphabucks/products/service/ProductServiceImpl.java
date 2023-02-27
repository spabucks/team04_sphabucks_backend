package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.vo.RequestProduct;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;
    @Override
    public void addProduct(RequestProduct requestProduct) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);

        iProductRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return iProductRepository.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }
}
