package sphabucks.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.BigCategory;
import sphabucks.products.service.IBigCategoryService;
import sphabucks.products.vo.RequestBigCategory;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/api/bigCategory")
@RequiredArgsConstructor
@Tag(name = "상품")
public class BigCategoryController {

    private final IBigCategoryService iBigCategoryService;

    @PostMapping("/v1/add")
    @Operation(summary = "상품에 대분류 물리기", description = "어드민 권한 - 삭제 예정?")
    public void addBigCategory(@RequestBody RequestBigCategory requestBigCategory){
        iBigCategoryService.addBigCategory(requestBigCategory);
    }

    @GetMapping("/v1/get/{bigCategoryId}")
    @Operation(summary = "대분류 조회", description = "상품 조회도 아닌 단순한 대분류 정보 조회 - 삭제 예정?")
    public BigCategory getBigCategory(@PathVariable Long bigCategoryId){
        return iBigCategoryService.getBigCategory(bigCategoryId);
    }

    @GetMapping("/v1/get/all")
    @Operation(summary = "모든 대분류 조회")
    public List<BigCategory> getAll(){
        return iBigCategoryService.getAll();
    }

}
