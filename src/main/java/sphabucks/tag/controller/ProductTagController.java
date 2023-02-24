package sphabucks.tag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.service.IProductTagService;
import sphabucks.products.model.Product;
import sphabucks.tag.model.Tag;
import sphabucks.tag.vo.RequsetProductTag;

@RestController
@RequestMapping("/producttag")
@RequiredArgsConstructor
public class ProductTagController {
    private IProductTagService iProductTagService;

    @PostMapping("/add")
    public ProductTag addProductTag(@RequestBody RequsetProductTag requsetProductTag){
        return iProductTagService.addProductTag(requsetProductTag);
    }

    @PostMapping("/get/{id}")
    public ProductTag getProductTag(@PathVariable Long id){
        return null;
    }
}
