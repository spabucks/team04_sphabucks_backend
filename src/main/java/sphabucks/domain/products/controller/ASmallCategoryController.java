package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.service.ISmallCategoryService;
import sphabucks.domain.products.vo.RequestSmallCategory;

@RestController
@RequestMapping("/admin/api/v1/smallCategory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "소분류 어드민")
public class ASmallCategoryController {

    private final ISmallCategoryService iSmallCategoryService;

    @PostMapping("/add")
    @Operation(summary = "상품에 소분류 물리기", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addSmallCategory(@RequestBody RequestSmallCategory requestSmallCategory){
        iSmallCategoryService.addSmallCategory(requestSmallCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
