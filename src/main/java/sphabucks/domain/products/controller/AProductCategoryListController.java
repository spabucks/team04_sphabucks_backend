package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.service.IProductCategoryListService;
import sphabucks.domain.products.vo.RequestProductCategoryList;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/admin/api/v1/product-category")
@RequiredArgsConstructor
@Tag(name = "상품 분류 중간 테이블 어드민")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class AProductCategoryListController {

    private final IProductCategoryListService iProductCategoryListService;

    @PostMapping("/add")
    @Operation(summary = "상품에 대-소분류 물리기", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addProductCategoryList(@RequestBody RequestProductCategoryList requestProductCategoryList){
        iProductCategoryListService.addProductCategoryList(requestProductCategoryList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
