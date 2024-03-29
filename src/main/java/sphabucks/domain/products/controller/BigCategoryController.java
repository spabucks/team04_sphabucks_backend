package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.service.IBigCategoryService;
import sphabucks.domain.products.vo.RequestBigCategory;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/api/v1/bigCategory")
@RequiredArgsConstructor
@Tag(name = "상품 대분류")
public class BigCategoryController {

    private final IBigCategoryService iBigCategoryService;

    @GetMapping("/get/{bigCategoryId}")
    @Operation(summary = "대분류 조회", description = "상품 조회도 아닌 단순한 대분류 정보 조회")
    public ResponseEntity<Object> getBigCategory(@PathVariable Long bigCategoryId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,
                iBigCategoryService.getBigCategory(bigCategoryId)));
    }

    @GetMapping("/get/all")
    @Operation(summary = "모든 대분류 조회")
    public ResponseEntity<Object> getAll(){
        iBigCategoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iBigCategoryService.getAll()));
    }

}
