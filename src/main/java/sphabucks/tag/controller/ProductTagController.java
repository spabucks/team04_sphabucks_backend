package sphabucks.product_tag_list.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.product_tag_list.model.ProductTag;
import sphabucks.product_tag_list.service.IProductTagService;
import sphabucks.products.model.Product;
import sphabucks.tag.model.Tag;

@RestController
@RequestMapping("/producttag")
@RequiredArgsConstructor
public class ProductTagController {
    private IProductTagService iProductTagService;

    @PostMapping("/add")
    public ProductTag addProductTag(@RequestBody ProductTag productTag, Product product, Tag tag){
        return iProductTagService.addProductTag(productTag, product, tag);
    }

    @PostMapping("/get/{id}")
    public ProductTag getProductTag(@PathVariable Long id){
        return iProductTagService.getProductTag(id);
    }
}
