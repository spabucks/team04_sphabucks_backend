package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.service.IProductService;
import sphabucks.domain.products.vo.RequestProduct;

@RestController
@RequestMapping("/admin/api/v1/product")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "상품 어드민")
public class AProductController {

    private final IProductService iProductService;

    @PostMapping("/add")
    @Operation(summary = "상품 추가", description = "어드민 권한")
    public ResponseEntity<Object> addProduct(@RequestBody RequestProduct requestProduct) {
        iProductService.addProduct(requestProduct);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
