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
    public List<ProductTag> getByProductId(Long productId) {
        return iProductTagRepository.findAllByProductId(productId);
    }

    @Override
    public List<ResponseProductTag> getAllByTagId() {

        List<ResponseProductTag> responseProductTags = new ArrayList<>();                             // 최종 반환되는 객체
        for(int i=0;i<iProductTagRepository.count();i++){
            Long tagId = iTagRepository.findAll().get(i).getId();

            List<ResponseExhibitionProduct> responseExhibitionProducts = new ArrayList<>();            //  기획전 상품
            List<Product> products = iProductRepository.findAllById(iProductTagRepository.findAllOrderByTagId(tagId).get(i).getProduct().getId());
//            List<ProductImage> productImages = iProductImageRepo.findAllByProductId(products.get(i).getId());

            List<ProductImage> pI = iProductImageRepo.findAllByProductId(products.get(i).getId());
            ExhibitionProductImage exhibitionProductImage = null;
            if(iProductImageRepo.findByProductId(products.get(i).getId()).getChk() == 1){
                exhibitionProductImage.setProductId(products.get(i).getId());
                exhibitionProductImage.setImage(pI.get(i).getImage());
                exhibitionProductImage.setChk(pI.get(i).getChk());
                exhibitionProductImage.setAlt(pI.get(i).getAlt());
            }
//            ExhibitionProductImage exhibitionProductImages = ExhibitionProductImage.builder()
//                    .image(iProductImageRepo.findByProductId(products.get(i).getId()).getImage())
//                    .alt(iProductImageRepo.findByProductId(products.get(i).getId()).getAlt())
//                    .chk(iProductImageRepo.findByProductId(products.get(i).getId()).getChk())
//                    .productId(products.get(i).getId())
//                    .build();


//            ExhibitionProductImage exhibitionProductImages = ExhibitionProductImage.builder()
//                    .image(iProductImageRepo.findByProductId(products.get(i).getId()).getImage())
//                    .alt(iProductImageRepo.findByProductId(products.get(i).getId()).getAlt())
//                    .chk(iProductImageRepo.findByProductId(products.get(i).getId()).getChk())
//                    .productId(products.get(i).getId())
//                    .build();

            products.forEach(eProduct ->{
                responseExhibitionProducts.add(ResponseExhibitionProduct.builder()
                        .price(eProduct.getPrice())
                        .tagId(tagId)
                        .name(eProduct.getName())
                        .isNew(eProduct.getIsNew())
                        .best(eProduct.getIsBest())
                        .productId(eProduct.getId())
                        .productImage(exhibitionProductImage)
                        .build());
            });


            responseProductTags.add(ResponseProductTag.builder()
                    .tagId(tagId)
                    .tagImage(iTagRepository.findAll().get(i).getImage())
                    .productTagId(iProductTagRepository.findAll().get(i).getProduct().getId())
                    .responseExhibitionProduct(responseExhibitionProducts)
                    .build());

        }


        return responseProductTags;
    }


}
