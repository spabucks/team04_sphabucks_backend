package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.model.BigCategory;
import sphabucks.domain.products.service.ISmallCategoryService;
import sphabucks.domain.products.vo.RequestSmallCategory;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/smallCategory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "소분류")
public class SmallCategoryController {

    private final ISmallCategoryService iSmallCategoryService;

    @GetMapping("/get/{smallCategoryId}")
    @Operation(summary = "소분류 조회", description = "상품 조회도 아닌 단순한 소분류 정보 조회")
    public ResponseEntity<Object> getSmallCategory(@PathVariable Long smallCategoryId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iSmallCategoryService.getSmallCategory(smallCategoryId)));
    }

    @GetMapping("/get/all")
    @Operation(summary = "모든 소분류 조회")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iSmallCategoryService.getAll()));
    }

    @GetMapping("/get/type/{bigCategoryId}")
    @Operation(summary = "대분류에 속해있는 소뷴류들 가져오기")
    @Tag(name = "검색")
    public ResponseEntity<Object> getAllByType(@PathVariable BigCategory bigCategoryId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iSmallCategoryService.getAllByType(bigCategoryId)));
    }

}
