package sphabucks.productimage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sphabucks.productimage.model.ProductImage;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.productimage.service.IProductImageService;
import sphabucks.productimage.vo.RequestProductImage;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productImage")
@RequiredArgsConstructor
public class ProductImageController {
    private final IProductImageService iProductImageService;

    @PostMapping("/add")
    public void addProductImage(@RequestBody RequestProductImage requestProductImage){
        iProductImageService.addProductImage(requestProductImage);
    }

    @GetMapping("/get/{id}")
    public List<ProductImage> getProductImage(@PathVariable Long id){
        return iProductImageService.getProductImage(id);
    }

    @GetMapping("/get/all")
    public List<ProductImage> getAll(){
        return iProductImageService.getAll();
    }

}
