package sphabucks.tag.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.repository.IProductRepository;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.model.Tag;
import sphabucks.tag.repository.IProductTagRepository;
import sphabucks.tag.repository.ITagRepository;
import sphabucks.tag.vo.RequsetProductTag;
import sphabucks.tag.vo.ResponseProductTag;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductTagServiceImpl implements IProductTagService {

    private final IProductTagRepository iProductTagRepository;
    private final IProductRepository iProductRepository;
    private final ITagRepository iTagRepository;
    private final IProductImageRepo iProductImageRepo;

    @Override
    public void addProductTag(RequsetProductTag requsetProductTag) {
            ProductTag productTag = ProductTag.builder()
                    .product(iProductRepository.findById(requsetProductTag.getProductId()).get())
                    .tag(iTagRepository.findById(requsetProductTag.getTagId()).get())
                    .build();

        iProductTagRepository.save(productTag);
    }

    @Override
    public List<ProductTag> getByProductId(Long productId) {
        return iProductTagRepository.findAllByProductId(productId);
    }

    @Override
    public ResponseProductTag getByTagId(Long tagId) {


        ResponseProductTag responseProductTag = ResponseProductTag.builder()
                .productTagId(iProductTagRepository.findByTagId(tagId).getId())
                .tagImage(iTagRepository.findById(tagId).get().getImage())
                .productImage(iProductImageRepo.findById(iProductRepository.findById(iProductTagRepository.findByTagId(tagId).getId()).get().getId()).get().getImage())
                .price(iProductRepository.findById(iProductTagRepository.findByTagId(tagId).getId()).get().getPrice())
                .name(iProductRepository.findById(iProductTagRepository.findByTagId(tagId).getId()).get().getName())
                .productId(iProductRepository.findById(iProductTagRepository.findByTagId(tagId).getId()).get().getId())
                .tagId(tagId)
                .build();


        return responseProductTag;
    }


}
