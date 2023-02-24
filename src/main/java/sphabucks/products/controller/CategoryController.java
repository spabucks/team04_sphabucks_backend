package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Category;
import sphabucks.products.service.ICategoryService;

import java.util.List;

@RestController

@RequestMapping("v1/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;

    @PostMapping("/add")
    public void addCategory(@RequestBody Category category){
        iCategoryService.addCategory(category);
    }

    @GetMapping("/get/{categoryId}")
    public Category getCategory(@PathVariable Integer categoryId){
        return iCategoryService.getCategory(categoryId);
    }

    @GetMapping("/get/all")
    public List<Category> getAll(){
        return iCategoryService.getAll();
    }

    @GetMapping("/get/type/{type}")
    public List<Category> getAllByType(@PathVariable String type){
        return iCategoryService.getAllByType(type);
    }
}
