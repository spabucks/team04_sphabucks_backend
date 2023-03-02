package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.vo.RequestProduct;
import sphabucks.products.vo.ResponseProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;
    @Override
    public void addProduct(RequestProduct requestProduct) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(requestProduct, Product.class);

        iProductRepository.save(product);
    }

    @Override
    public ResponseProduct getProduct(Long id) {
        Product product = iProductRepository.findById(id).get();
        List<ProductImage> productImages = iProductImageRepo.findAllByProductId(id);
        String thumbnail = productImages.get(0).getImage();
        List<String> detailImages = new ArrayList<>();
        for (int i=1; i<productImages.size(); i++) detailImages.add(productImages.get(i).getImage());
        return ResponseProduct.builder()
                .id(id)
                .title(product.getName())
                .imgUrl(thumbnail)
                .description(product.getDescription())
                .price(product.getPrice())
                .productDetailImgUrl(detailImages)
                .build();
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }
}
