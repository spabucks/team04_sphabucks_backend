package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.model.BigCategory;
import sphabucks.domain.products.service.IBigCategoryService;
import sphabucks.domain.products.vo.RequestBigCategory;
import sphabucks.global.responseEntity.ResponseDTO;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/api/v1/bigCategory")
@RequiredArgsConstructor
@Tag(name = "상품")
public class BigCategoryController {

    private final IBigCategoryService iBigCategoryService;

    @PostMapping("/add")
    @Operation(summary = "상품에 대분류 물리기", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addBigCategory(@RequestBody RequestBigCategory requestBigCategory){
        iBigCategoryService.addBigCategory(requestBigCategory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/get/{bigCategoryId}")
    @Operation(summary = "대분류 조회", description = "상품 조회도 아닌 단순한 대분류 정보 조회 - 삭제 예정?")
    public ResponseEntity<Object> getBigCategory(@PathVariable Long bigCategoryId){
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK,iBigCategoryService.getBigCategory(bigCategoryId)));
    }

    @GetMapping("/get/all")
    @Operation(summary = "모든 대분류 조회")
    public List<BigCategory> getAll(){
        return iBigCategoryService.getAll();
    }

}
