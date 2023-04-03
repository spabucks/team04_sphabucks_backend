package sphabucks.domain.productimage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.domain.productimage.model.ProductImage;
import sphabucks.domain.productimage.vo.RequestProductImage;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.productimage.repository.IProductImageRepo;

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
