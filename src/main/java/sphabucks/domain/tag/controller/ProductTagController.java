package sphabucks.domain.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.tag.service.IProductTagService;
import sphabucks.domain.tag.vo.RequestProductTag;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/product-tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "상품 태그")
public class ProductTagController {
    private final IProductTagService iProductTagService;


    @GetMapping("/get/{id}")
    @Operation(summary = "상품의 태그 조회")
    public ResponseEntity<Object> getByProductId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iProductTagService.getProductId(id)));
    }

    @GetMapping("/exhibition/get/all")
    @Operation(summary = "기획전")
    @Tag(name = "검색")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,iProductTagService.getAll()));
    }

    @GetMapping("/exhibition/get/{tagId}")
    @Operation(summary = "기획전")
    @Tag(name = "검색")
    public ResponseEntity<Object> getTagId(@PathVariable Long tagId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,iProductTagService.getTagId(tagId)));
    }
}
