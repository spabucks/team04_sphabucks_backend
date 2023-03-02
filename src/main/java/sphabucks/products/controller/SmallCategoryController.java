package sphabucks.products.controller;

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
public class SmallCategoryController {

    private final ISmallCategoryService iSmallCategoryService;

    @PostMapping("/add")
    public void addSmallCategory(@RequestBody RequestSmallCategory requestSmallCategory){
        iSmallCategoryService.addSmallCategory(requestSmallCategory);
    }

    @GetMapping("/get/{smallCategoryId}")
    public SmallCategory getBigCategory(@PathVariable Integer smallCategoryId){
        return iSmallCategoryService.getSmallCategory(smallCategoryId);
    }

    @GetMapping("/get/all")
    public List<SmallCategory> getAll(){
        return iSmallCategoryService.getAll();
    }

    @GetMapping("/get/type/{bigCategoryId}")
    public List<SmallCategory> getAllByType(@PathVariable BigCategory bigCategoryId){
        return iSmallCategoryService.getAllByType(bigCategoryId);
    }

}
