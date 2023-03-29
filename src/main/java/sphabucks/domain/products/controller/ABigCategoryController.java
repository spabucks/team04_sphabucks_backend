package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.service.IBigCategoryService;
import sphabucks.domain.products.vo.RequestBigCategory;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/admin/api/v1/bigCategory")
@RequiredArgsConstructor
@Tag(name = "상품 대분류 어드민")
public class ABigCategoryController {

    private final IBigCategoryService iBigCategoryService;

    @PostMapping("/add")
    @Operation(summary = "상품에 대분류 물리기", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addBigCategory(@RequestBody RequestBigCategory requestBigCategory){
        iBigCategoryService.addBigCategory(requestBigCategory);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
