package sphabucks.domain.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.tag.service.IProductTagService;
import sphabucks.domain.tag.vo.RequestProductTag;

@RestController
@RequestMapping("/admin/api/v1/product-tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "상품 태그 어드민" )
public class AProductTagController {

    private final IProductTagService iProductTagService;

    @PostMapping("/add")
    @Operation(summary = "상품에 태그 물리기", description = "어드민 권한")
    public ResponseEntity<Object> addProductTag(@RequestBody RequestProductTag requestProductTag){
        iProductTagService.addProductTag(requestProductTag);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
