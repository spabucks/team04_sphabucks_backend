package sphabucks.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.repository.IProductTagRepository;
import sphabucks.tag.repository.ITagRepository;
import sphabucks.tag.vo.ExhibitionProductImage;
import sphabucks.tag.vo.RequestProductTag;
import sphabucks.tag.vo.ResponseExhibitionProduct;
import sphabucks.tag.vo.ResponseProductTag;

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
        ProductTag productTag = ProductTag.builder()
                .product(iProductRepository.findById(requestProductTag.getProductId()).get())
                .tag(iTagRepository.findById(requestProductTag.getTagId()).get())
                .build();

        iProductTagRepository.save(productTag);
    }

    @Override
    public List<ProductTag> getProductId(Long productId) {
        return iProductTagRepository.findAllByProductId(productId);
    }

    @Override
    public List<ResponseProductTag> getAll() {

        List<ResponseProductTag> responseProductTags = new ArrayList<>();                             // 최종 반환되는 객체
        List<ProductTag> productTagList = iProductTagRepository.findAll();


        for(int i=0;i< iTagRepository.count();i++){
            List<ResponseExhibitionProduct> responseExhibitionProducts = new ArrayList<>();
            for(int j=0;j< productTagList.size();j++){
                Long productId = productTagList.get(j).getProduct().getId();
                Long ptagId = productTagList.get(j).getTag().getId();

                List<ProductImage> productImageList = iProductImageRepo.findAllByProductIdAndChk(productId,1);

                ExhibitionProductImage exhibitionProductImage = new ExhibitionProductImage();
                exhibitionProductImage.setImage(productImageList.get(0).getImage());

                if((ptagId-1) == i){
                    Product product = iProductRepository.findById(productId).get();
                    responseExhibitionProducts.add(ResponseExhibitionProduct.builder()
                            .price(product.getPrice())
                            .name(product.getName())
                            .isNew(product.getIsNew())
                            .isBest(product.getIsBest())
                            .productId(product.getId())
                            .productImage(exhibitionProductImage)
                            .build());
                }
            }

            Long tagId = iTagRepository.findAll().get(i).getId();
            responseProductTags.add(ResponseProductTag.builder()
                    .tagId(tagId)
                    .tagImage(iTagRepository.findAll().get(i).getImage())
                    .tagName(iTagRepository.findAll().get(i).getName())
                    .responseExhibitionProduct(responseExhibitionProducts)
                    .build());
        }

        return responseProductTags;
    }


}
