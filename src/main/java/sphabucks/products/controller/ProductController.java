package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Product;
import sphabucks.products.service.IProductService;
import sphabucks.products.vo.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
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

    // 상품 검색 메서드 (키워드 검색)
    @GetMapping("/search")
    public List<ResponseSearchProduct> searchProductKeyword(@RequestParam("keyword") String keyword){

        return iProductService.searchProductKeyword(keyword);
    }

    // 상품 검색 상단 필터 메뉴 호출 (키워드 검색)
    @GetMapping("/search-menu")
    public ResponseSearchMenu searchProductKeywordMenu(@RequestParam("keyword") String keyword) {
        return iProductService.searchProductKeywordMenu(keyword);
    }


}
