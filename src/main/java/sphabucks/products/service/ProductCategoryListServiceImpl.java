package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.ICategoryRepository;
import sphabucks.products.repository.IProductCategoryListRepository;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.vo.RequestProductCategoryList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements IProductCategoryListService{

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IProductRepository iProductRepository;
    private final ICategoryRepository iCategoryRepository;

    @Override
    public void addProductCategoryList(RequestProductCategoryList requestProductCategoryList) {
        iProductCategoryListRepository.save(ProductCategoryList.builder()
                .category(iCategoryRepository.findById(requestProductCategoryList.getCategoryId()).get())
                .product(iProductRepository.findById(requestProductCategoryList.getProductId()).get())
                .build()
        );
    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {
        return iProductCategoryListRepository.findAllByProductId(productId);
    }
}
