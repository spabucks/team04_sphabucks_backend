package sphabucks.domain.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.products.service.IProductCategoryListService;
import sphabucks.domain.products.model.ProductCategoryList;
import sphabucks.domain.products.vo.RequestProductCategoryList;
import sphabucks.domain.products.vo.ResponseProductList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-category")
@RequiredArgsConstructor
@Tag(name = "상품")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ProductCategoryListController {
    private final IProductCategoryListService iProductCategoryListService;

    @PostMapping("/add")
    @Operation(summary = "상품에 대-소분류 물리기", description = "어드민 권한 - 삭제 예정?")
    public void addProductCategoryList(@RequestBody RequestProductCategoryList requestProductCategoryList){
        iProductCategoryListService.addProductCategoryList(requestProductCategoryList);
    }

    @GetMapping("/get/{productId}")
    @Operation(summary = "상품의 대-소분류 조회")
    public List<ProductCategoryList> getAllByProduct(@PathVariable Long productId){
        return iProductCategoryListService.getByProductId(productId);
    }

    @GetMapping("/get-others/{id}")
    @Operation(summary = "함께 본 상품", description = "상품 상세 정보 조회 때만 동작 해야함")
    public List<ResponseProductList> getOtherProducts(@PathVariable Long id) {
        return iProductCategoryListService.getOtherProductByProductId(id);
    }
}
