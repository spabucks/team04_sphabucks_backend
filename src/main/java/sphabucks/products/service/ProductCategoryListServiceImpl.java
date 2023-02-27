package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.*;
import sphabucks.products.vo.RequestProductCategoryList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements IProductCategoryListService{

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IProductRepository iProductRepository;
    private final IBigCategoryRepository iBigCategoryRepository;
    private final ISmallCategoryRepository iSmallCategoryRepository;

    @Override
    public void addProductCategoryList(RequestProductCategoryList requestProductCategoryList) {
        iProductCategoryListRepository.save(ProductCategoryList.builder()
                .bigCategory(iBigCategoryRepository.findById(requestProductCategoryList.getBigCategoryId()).get())
                .smallCategory(iSmallCategoryRepository.findById(requestProductCategoryList.getSmallCategoryId()).get())
                .product(iProductRepository.findById(requestProductCategoryList.getProductId()).get())
                .build()
        );
    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {
        return iProductCategoryListRepository.findAllByProductId(productId);
    }
}
