package sphabucks.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.repository.IProductTagRepository;
import sphabucks.products.model.Product;
import sphabucks.tag.model.Tag;

@RequiredArgsConstructor
@Service
public class ProductTagImpl implements IProductTagService {

    private final IProductTagRepository iProductTagRepository;


    @Override
    public ProductTag addProductTag(ProductTag productTag, Product product, Tag tag) {
        return iProductTagRepository.save(productTag);
    }

    @Override
    public ProductTag getProductTag(Long id) {
        return iProductTagRepository.findById(id).get();
    }
}
