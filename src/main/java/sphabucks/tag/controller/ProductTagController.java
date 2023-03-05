package sphabucks.tag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.service.IProductTagService;
import sphabucks.tag.vo.RequestProductTag;
import sphabucks.tag.vo.ResponseProductTag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-tag")
@RequiredArgsConstructor
public class ProductTagController {
    private final IProductTagService iProductTagService;

    @PostMapping("/add")
    public void addProductTag(@RequestBody RequestProductTag requestProductTag){
        iProductTagService.addProductTag(requestProductTag);
    }

    @GetMapping("/get/{id}")
    public List<ProductTag> getByProductId(@PathVariable Long id){
        return iProductTagService.getByProductId(id);
    }

    @GetMapping("/get/productTag/all")
    public List<ResponseProductTag> getAllByTagId(){
        return iProductTagService.getAllByTagId();
    }
}
