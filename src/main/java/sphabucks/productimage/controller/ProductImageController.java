package sphabucks.productimage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "상품")
public class ProductImageController {
    private final IProductImageService iProductImageService;

    @PostMapping("/add")
    @Operation(summary = "상품 이미지 정보 등록", description = "어드민 권한 - 삭제 예정?")
    public void addProductImage(@RequestBody RequestProductImage requestProductImage){
        iProductImageService.addProductImage(requestProductImage);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "상품 이미지 정보 확인", description = "어디 쓰이는 놈이지?")
    public List<ProductImage> getProductImage(@PathVariable Long id){
        return iProductImageService.getProductImage(id);
    }

    @GetMapping("/get/all")
    @Operation(summary = "전체 상품 이미지 확인", description = "어드민 권한 - 삭제 예정?")
    public List<ProductImage> getAll(){
        return iProductImageService.getAll();
    }

}
