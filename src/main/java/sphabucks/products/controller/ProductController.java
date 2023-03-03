package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Product;
import sphabucks.products.service.IProductService;
import sphabucks.products.vo.RequestProduct;
import sphabucks.products.vo.ResponseProduct;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/add")
    public void addProduct(@RequestBody RequestProduct requestProduct) {
        iProductService.addProduct(requestProduct);
    }

    @GetMapping("/get/{productId}")
    public ResponseProduct getProduct(@PathVariable Long productId) {
        return iProductService.getProduct(productId);
    }

    @GetMapping("/get/all")
    public List<Product> getAll(){
        return iProductService.getAll();
    }

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    @GetMapping("/get-best/{bigCategoryId}")
    public List<ResponseProduct> getBestBigCategory(@PathVariable Integer bigCategoryId) {

        return iProductService.getBestBigCategory(bigCategoryId);
    }

    // 베스트 상품 조회 메서드 (소분류 카테고리별 조회)

}
