package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Product;
import sphabucks.products.service.IProductService;
import sphabucks.products.vo.RequestProduct;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/add")
    public void addProduct(@RequestBody RequestProduct requestProduct) {
        iProductService.addProduct(requestProduct);
    }

    @GetMapping("/get/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return iProductService.getProduct(productId);
    }

    @GetMapping("/get/all")
    public List<Product> getAll(){
        return iProductService.getAll();
    }
}
