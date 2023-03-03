package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.*;
import sphabucks.products.vo.RequestProductCategoryList;
import sphabucks.products.vo.ResponseOtherProducts;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements IProductCategoryListService{

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final ISmallCategoryRepository iSmallCategoryRepository;
    private final IBigCategoryRepository iBigCategoryRepository;
    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;

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

    @Override
    public List<ResponseOtherProducts> getOtherProductByProductId(Long productId) {
        Integer currentSmallCategoryId = iProductCategoryListRepository.findAllByProductId(productId).get(0)
                .getSmallCategory().getId();    // 현재 상품의 소분류 id
        List<ProductCategoryList> productCategoryLists =
                iProductCategoryListRepository.findAllBySmallCategoryId(currentSmallCategoryId);    // 소분류가 같은 제품들

        List<ResponseOtherProducts> return_value = new ArrayList<>();
        for (ProductCategoryList productCategoryList : productCategoryLists) {
            Long otherProductId = productCategoryList.getProduct().getId();
            if (otherProductId.equals(productId)) continue;
            Product otherProduct =  iProductRepository.findById(otherProductId).get();
            ResponseOtherProducts responseOtherProducts = ResponseOtherProducts.builder()
                    .id(otherProductId)
                    .name(otherProduct.getName())
                    .imgUrl(iProductImageRepo.findAllByProductId(otherProductId).get(0).getImage())
                    .price(otherProduct.getPrice())
                    .build();
            return_value.add(responseOtherProducts);
        }
        return return_value;
    }
}
