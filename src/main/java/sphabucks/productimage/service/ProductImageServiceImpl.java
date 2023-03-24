package sphabucks.productimage.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.productimage.vo.RequestProductImage;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.repository.ITagRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductImageServiceImpl implements IProductImageService{

    private final IProductImageRepo iProductImageRepo;
    private final IProductRepository iProductRepository;

    @Override
    public void addProductImage(RequestProductImage requestProductImage) {

        ProductImage productImage = ProductImage.builder()
                .product(iProductRepository.findById(requestProductImage.getProductId())
                        .orElseThrow(()->new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode())))
                .image(requestProductImage.getImage())
                .alt(requestProductImage.getAlt())
                .chk(requestProductImage.getChk())
                .build();
        iProductImageRepo.save(productImage);
    }

    @Override
    public List<ProductImage> getProductImage(Long id) {

        if(iProductImageRepo.findAllByProductId(id).isEmpty()){
            throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS, ErrorCode.IMAGE_NOT_EXISTS.getCode());
        }

        return iProductImageRepo.findAllByProductId(id);
    }


    @Override
    public List<ProductImage> getAll() {

        if(iProductImageRepo.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.IMAGES_NOT_EXISTS, ErrorCode.IMAGES_NOT_EXISTS.getCode());
        }

        return iProductImageRepo.findAll();
    }
}
