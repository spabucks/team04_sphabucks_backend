package sphabucks.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.BigCategory;
import sphabucks.products.model.SmallCategory;
import sphabucks.products.service.ISmallCategoryService;
import sphabucks.products.vo.RequestSmallCategory;

import java.util.List;

@RestController

@RequestMapping("/api/v1/smallCategory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "상품")
public class SmallCategoryController {

    private final ISmallCategoryService iSmallCategoryService;

    @PostMapping("/add")
    @Operation(summary = "상품에 소분류 물리기", description = "어드민 권한 - 삭제 예정?")
    public void addSmallCategory(@RequestBody RequestSmallCategory requestSmallCategory){
        iSmallCategoryService.addSmallCategory(requestSmallCategory);
    }

    @GetMapping("/get/{smallCategoryId}")
    @Operation(summary = "소분류 조회", description = "상품 조회도 아닌 단순한 소분류 정보 조회 - 삭제 예정?")
    public SmallCategory getSmallCategory(@PathVariable Long smallCategoryId){
        return iSmallCategoryService.getSmallCategory(smallCategoryId);
    }

    @GetMapping("/get/all")
    @Operation(summary = "모든 소분류 조회", description = "필요 없어 보이므로 삭제 예정?")
    public List<SmallCategory> getAll(){
        return iSmallCategoryService.getAll();
    }

    @GetMapping("/get/type/{bigCategoryId}")
    @Operation(summary = "대분류에 속해있는 소뷴류들 가져오기")
    @Tag(name = "검색")
    public List<SmallCategory> getAllByType(@PathVariable BigCategory bigCategoryId){
        return iSmallCategoryService.getAllByType(bigCategoryId);
    }

}
