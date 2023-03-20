package sphabucks.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.service.IProductTagService;
import sphabucks.tag.vo.RequestProductTag;
import sphabucks.tag.vo.ResponseProductTag;

import java.util.List;

@RestController
@RequestMapping("/api/product-tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "태그")
public class ProductTagController {
    private final IProductTagService iProductTagService;

    @PostMapping("/v1/add")
    @Operation(summary = "상품에 태그 물리기", description = "어드민 권한 - 삭제 예정?")
    public void addProductTag(@RequestBody RequestProductTag requestProductTag){
        iProductTagService.addProductTag(requestProductTag);
    }

    @GetMapping("/v1/get/{id}")
    @Operation(summary = "상품의 태그 조회")
    public List<ProductTag> getByProductId(@PathVariable Long id){
        return iProductTagService.getProductId(id);
    }

    @GetMapping("/v1/exhibition/get/all")
    @Operation(summary = "기획전", description = "얘는 뭐고")
    @Tag(name = "검색")
    public List<ResponseProductTag> getAll(){
        return iProductTagService.getAll();
    }

    @GetMapping("/v1/exhibition/get/{tagId}")
    @Operation(summary = "기획전", description = "얘는 뭐지")
    @Tag(name = "검색")
    public ResponseProductTag getTagId(@PathVariable Long tagId){
        return iProductTagService.getTagId(tagId);
    }
}
