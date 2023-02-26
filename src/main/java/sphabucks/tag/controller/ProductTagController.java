package sphabucks.tag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.service.IProductTagService;
import sphabucks.tag.vo.RequsetProductTag;

import java.util.List;

@RestController
@RequestMapping("/product-tag")
@RequiredArgsConstructor
public class ProductTagController {
    private IProductTagService iProductTagService;

    @PostMapping("/add")
    public void addProductTag(@RequestBody RequsetProductTag requsetProductTag){
        iProductTagService.addProductTag(requsetProductTag);
    }

    @PostMapping("/get/{id}")
    public List<ProductTag> getByProductId(@PathVariable Long id){
        return iProductTagService.getByProductId(id);
    }
}
