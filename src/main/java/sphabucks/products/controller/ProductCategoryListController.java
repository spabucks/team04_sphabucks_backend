package sphabucks.products.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.service.IProductCategoryListService;
import sphabucks.products.vo.RequestProductCategoryList;
import sphabucks.products.vo.ResponseOtherProducts;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-category")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ProductCategoryListController {
    private final IProductCategoryListService iProductCategoryListService;

    @PostMapping("/add")
    public void addProductCategoryList(@RequestBody RequestProductCategoryList requestProductCategoryList){
        iProductCategoryListService.addProductCategoryList(requestProductCategoryList);
    }

    @GetMapping("/get/{productId}")
    public List<ProductCategoryList> getAllByProduct(@PathVariable Long productId){
        return iProductCategoryListService.getByProductId(productId);
    }

    @GetMapping("/get-others/{id}")
    public List<ResponseOtherProducts> getOtherProducts(@PathVariable Long id) {
        return iProductCategoryListService.getOtherProductByProductId(id);
    }
}
