package sphabucks.domain.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.tag.repository.IProductTagRepository;
import sphabucks.domain.tag.vo.ExhibitionProductImage;
import sphabucks.domain.tag.vo.RequestProductTag;
import sphabucks.domain.tag.vo.ResponseExhibitionProduct;
import sphabucks.domain.tag.vo.ResponseProductTag;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.productimage.model.ProductImage;
import sphabucks.domain.productimage.repository.IProductImageRepo;
import sphabucks.domain.tag.model.ProductTag;
import sphabucks.domain.tag.repository.ITagRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductTagServiceImpl implements IProductTagService {

    private final IProductTagRepository iProductTagRepository;
    private final IProductRepository iProductRepository;
    private final ITagRepository iTagRepository;
    private final IProductImageRepo iProductImageRepo;

    @Override
    public void addProductTag(RequestProductTag requestProductTag) {

        if(iProductTagRepository.findByProductIdAndTagId(requestProductTag.getProductId(), requestProductTag.getTagId()).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_TAG, ErrorCode.DUPLICATE_TAG.getCode());
        }

        ProductTag productTag = ProductTag.builder()
                .product(iProductRepository.findById(requestProductTag.getProductId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode())))
                .tag(iTagRepository.findById(requestProductTag.getTagId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode())))
                .build();

        iProductTagRepository.save(productTag);
    }

    @Override
    public List<ProductTag> getProductId(Long productId) {
        if(iProductTagRepository.findAllByProductId(productId).isEmpty()){
            throw new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode());
        }
        return iProductTagRepository.findAllByProductId(productId);
    }

    @Override
    public List<ResponseProductTag> getAll() {

        List<ResponseProductTag> responseProductTags = new ArrayList<>();                             // 최종 반환되는 객체

        if(iProductTagRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode());
        }
        List<ProductTag> productTagList = iProductTagRepository.findAll();

        for (int i = 0; i < iTagRepository.count(); i++) {
            List<ResponseExhibitionProduct> responseExhibitionProducts = new ArrayList<>();
            for (ProductTag productTag : productTagList) {
                Long productId = productTag.getProduct().getId();
                Long pTagId = productTag.getTag().getId();

                if (iProductImageRepo.findAllByProductIdAndChk(productId, 1).isEmpty()) {
                    throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS, ErrorCode.IMAGES_NOT_EXISTS.getCode());
                }
                List<ProductImage> productImageList = iProductImageRepo.findAllByProductIdAndChk(productId, 1);

                ExhibitionProductImage exhibitionProductImage = new ExhibitionProductImage();
                exhibitionProductImage.setImage(productImageList.get(0).getImage());

                if ((pTagId - 1) == i) {
                    Product product = iProductRepository.findById(productId)
                            .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
                    responseExhibitionProducts.add(ResponseExhibitionProduct.builder()
                            .price(product.getPrice())
                            .title(product.getName())
                            .isNew(product.getIsNew())
                            .isBest(product.getIsBest())
                            .id(product.getId())
                            .amount(product.getAmount())
                            .imgUrl(productImageList.get(0).getImage())
                            .build());
                }
            }

            if(iTagRepository.findAll().isEmpty()){
                throw new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode());
            }
            Long tagId = iTagRepository.findAll().get(i).getId();
            responseProductTags.add(ResponseProductTag.builder()
                    .id(tagId)
                    .imgUrl(iTagRepository.findAll().get(i).getImage())
                    .tagName(iTagRepository.findAll().get(i).getName())
                    .data(responseExhibitionProducts)
                    .build());
        }

        return responseProductTags;
    }

    @Override
    public ResponseProductTag getTagId(Long tagId) {

        ResponseProductTag responseProductTagList;
        if(iProductTagRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode());
        }
        List<ProductTag> productTagList = iProductTagRepository.findAll();

        List<ResponseExhibitionProduct> responseExhibitionProducts = new ArrayList<>();
        for (ProductTag productTag : productTagList) {
            Long productId = productTag.getProduct().getId();
            Long pTagId = productTag.getTag().getId();

            if (iProductImageRepo.findAllByProductIdAndChk(productId, 1).isEmpty()) {
                throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS, ErrorCode.IMAGES_NOT_EXISTS.getCode());
            }
            List<ProductImage> productImageList = iProductImageRepo.findAllByProductIdAndChk(productId, 1);

            ExhibitionProductImage exhibitionProductImage = new ExhibitionProductImage();
            exhibitionProductImage.setImage(productImageList.get(0).getImage());

            if (pTagId.equals(tagId)) {
                Product product = iProductRepository.findById(productId)
                        .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
                responseExhibitionProducts.add(ResponseExhibitionProduct.builder()
                        .price(product.getPrice())
                        .title(product.getName())
                        .isNew(product.getIsNew())
                        .isBest(product.getIsBest())
                        .id(product.getId())
                        .amount(product.getAmount())
                        .imgUrl(productImageList.get(0).getImage())
                        .build());
            }
        }

        if(iTagRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode());
        }

        responseProductTagList = ResponseProductTag.builder()
                .id(tagId)
                .imgUrl(iTagRepository.findAllById(tagId).getImage())
                .tagName(iTagRepository.findAllById(tagId).getName())
                .data(responseExhibitionProducts)
                .build();



        return responseProductTagList;
    }


}
