package sphabucks.tag.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.products.repository.IProductRepository;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.repository.IProductTagRepository;
import sphabucks.tag.repository.ITagRepository;
import sphabucks.tag.vo.RequsetProductTag;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductTagServiceImpl implements IProductTagService {

    private final IProductTagRepository iProductTagRepository;
    private final IProductRepository iProductRepository;
    private final ITagRepository iTagRepository;

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

}
