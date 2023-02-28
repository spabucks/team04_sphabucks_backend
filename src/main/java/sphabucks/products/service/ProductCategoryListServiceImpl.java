package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.*;
import sphabucks.products.vo.RequestProductCategoryList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements IProductCategoryListService{

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final ISmallCategoryRepository iSmallCategoryRepository;
    private final IBigCategoryRepository iBigCategoryRepository;
    private final IProductRepository iProductRepository;

    @Override
    public void addProductCategoryList(RequestProductCategoryList requestProductCategoryList) {

        ProductCategoryList productCategoryList = ProductCategoryList.builder()
                .smallCategory(iSmallCategoryRepository.findById(requestProductCategoryList.getSmallCategoryId()).get())
                .bigCategory(iBigCategoryRepository.findById(requestProductCategoryList.getBigCategoryId()).get())
                .product(iProductRepository.findById(requestProductCategoryList.getProductId()).get())
                .build();

        iProductCategoryListRepository.save(productCategoryList);

    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {
        return iProductCategoryListRepository.findAllByProductId(productId);
    }
}
