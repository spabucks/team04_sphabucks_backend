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
//        String thumbnail = productImages.get(1).getImage();
//        ResponseProduct responseProduct = ResponseProduct.builder()
//                .productId(id)
//                .productThumbnailUrl(iProductImageRepo.findByProductIdAndChkIsTrue(id).getImage())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .productDetailImgUrl(iProductImageRepo.)
//                .build();
        return null;
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }
}
