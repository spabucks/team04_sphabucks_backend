package sphabucks.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.BigCategory;
import sphabucks.products.service.IBigCategoryService;
import sphabucks.products.vo.RequestBigCategory;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/api/v1/bigCategory")
@RequiredArgsConstructor
public class BigCategoryController {

    private final IBigCategoryService iBigCategoryService;

    @PostMapping("/add")
    public void addBigCategory(@RequestBody RequestBigCategory requestBigCategory){
        iBigCategoryService.addBigCategory(requestBigCategory);
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
