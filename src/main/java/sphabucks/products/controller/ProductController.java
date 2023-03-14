package sphabucks.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Product;
import sphabucks.products.service.IProductService;
import sphabucks.products.vo.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "상품")
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/add")
    @Operation(summary = "상품 추가", description = "어드민 권한 - 삭제 예정?")
    public void addProduct(@RequestBody RequestProduct requestProduct) {
        iProductService.addProduct(requestProduct);
    }

    @GetMapping("/get/{productId}")
    @Operation(summary = "상품 조회", description = "상품을 클릭했을 때 뜨는 상세정보")
    public ResponseProduct getProduct(@PathVariable Long productId) {
        return iProductService.getProduct(productId);
    }

    @GetMapping("/get/all")
    @Operation(summary = "전체 상품 조회", description = "구현 X")
    @Tag(name = "검색")
    public List<Product> getAll(){
        return iProductService.getAll();
    }

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    @GetMapping("/get-best/{bigCategoryId}")
    @Operation(summary = "베스트 상품 조회")
    @Tag(name = "검색")
    public List<ResponseProduct> getBestBigCategory(@PathVariable Integer bigCategoryId) {

        return iProductService.getBestBigCategory(bigCategoryId);
    }
    // 상품 검색 메서드 (키워드 검색)
    @GetMapping("/search")
    @Operation(summary = "키워드 검색", description = "돋보기 아이콘 클릭을 통해 들어간 검색창에서 키워드로 검색")
    @Tag(name = "검색")
    public List<ResponseSearchProduct> searchProductKeyword(@RequestParam("keyword") String keyword, @PageableDefault(size = 10) Pageable pageable){

        return iProductService.searchProductKeyword(keyword, pageable);
    }

    // 상품 검색 상단 필터 메뉴 호출 (키워드 검색)
    @GetMapping("/search-menu")
    @Operation(summary = "키워드 검색 시 필터메뉴", description = "키워드로 검색할 시 필터메뉴목록 출력")
    @Tag(name = "검색")
    public ResponseSearchMenu searchProductKeywordMenu(@RequestParam("keyword") String keyword, Pageable pageable) {
        return iProductService.searchProductKeywordMenu(keyword, pageable);
    }


}
