package sphabucks.domain.productimage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sphabucks.domain.productimage.service.IProductImageService;
import sphabucks.domain.productimage.vo.RequestProductImage;

@RestController
@RequestMapping("/admin/api/v1/productImage")
@RequiredArgsConstructor
@Tag(name = "상품 이미지 어드민")
public class AProductImageController {

    private final IProductImageService iProductImageService;

    @PostMapping("/add")
    @Operation(summary = "상품 이미지 정보 등록", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addProductImage(@RequestBody RequestProductImage requestProductImage){
        iProductImageService.addProductImage(requestProductImage);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
