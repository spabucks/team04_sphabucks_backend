package sphabucks.domain.productimage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.productimage.service.IProductImageService;
import sphabucks.domain.productimage.vo.RequestProductImage;

@RestController
@RequestMapping("/api/v1/productImage")
@RequiredArgsConstructor
@Tag(name = "상품")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ProductImageController {
    private final IProductImageService iProductImageService;

    @PostMapping("/add")
    @Operation(summary = "상품 이미지 정보 등록", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addProductImage(@RequestBody RequestProductImage requestProductImage){
        iProductImageService.addProductImage(requestProductImage);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "상품 이미지 정보 확인", description = "어디 쓰이는 놈이지?")
    public ResponseEntity<Object> getProductImage(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(iProductImageService.getProductImage(id));
    }

    @GetMapping("/get/all")
    @Operation(summary = "전체 상품 이미지 확인", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(iProductImageService.getAll());
    }

}
