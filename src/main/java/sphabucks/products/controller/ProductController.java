package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Product;
import sphabucks.products.service.IProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return iProductService.addProduct(product);
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable Long id) {
        return iProductService.getProduct(id);
    }
}
