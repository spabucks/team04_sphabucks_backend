package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Product;
import sphabucks.products.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return iProductService.addProduct(product);
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
