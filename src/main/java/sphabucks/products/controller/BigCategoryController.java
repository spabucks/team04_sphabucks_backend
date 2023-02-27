package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.BigCategory;
import sphabucks.products.model.Category;
import sphabucks.products.service.IBigCategoryService;
import sphabucks.products.service.ICategoryService;

import java.util.List;

@RestController

@RequestMapping("v1/api/bigCategory")
@RequiredArgsConstructor
public class BigCategoryController {

    private final IBigCategoryService iBigCategoryService;

    @PostMapping("/add")
    public void addBigCategory(@RequestBody BigCategory bigCategory){
        iBigCategoryService.addBigCategory(bigCategory);
    }

    @GetMapping("/get/{bigCategoryId}")
    public BigCategory getBigCategory(@PathVariable Integer bigCategoryId){
        return iBigCategoryService.getBigCategory(bigCategoryId);
    }

    @GetMapping("/get/all")
    public List<BigCategory> getAll(){
        return iBigCategoryService.getAll();
    }

}
