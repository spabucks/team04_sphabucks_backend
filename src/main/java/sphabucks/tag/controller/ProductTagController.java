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
    private final IProductTagService iProductTagService;

    @PostMapping("/add")
    public void addProductTag(@RequestBody RequsetProductTag requsetProductTag){
        iProductTagService.addProductTag(requsetProductTag);
    }

    @PostMapping("/get/{id}")
<<<<<<< HEAD
    public List<ProductTag> getByProductId(@PathVariable Long id){
        return iProductTagService.getByProductId(id);
=======
    public ProductTag getProductTag(@PathVariable Long id){
        return null;
>>>>>>> 047c564c842bfef7bfa862dffd6d5b3fde56e34f
    }
}
