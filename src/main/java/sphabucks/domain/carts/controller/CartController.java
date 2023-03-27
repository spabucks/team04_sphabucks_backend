package sphabucks.domain.carts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.carts.service.ICartService;
import sphabucks.carts.vo.*;
import sphabucks.domain.carts.vo.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Tag(name = "장바구니(카트)")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Slf4j
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/add")
    @Operation(summary = "장바구니 담기")
    Long addCart(@RequestBody RequestCart requestCart){
        return iCartService.addCart(requestCart);
    }

    @GetMapping("/get/{userId}")
    @Operation(summary = "장바구니 조회", description = "uuid 사용")
    List<ResponseGetCart> getCart(@PathVariable String userId){
        return iCartService.getCart(userId);
    }

    @GetMapping("/get/v2/{userId}")
    @Operation(summary = "장바구니 조회 v2", description = "유저의 카트 속 모든 정보를 한번에 반환해줌")
    List<ResponseCartV2> getCartV2(@PathVariable String userId) {
        return iCartService.getCartV2(userId);
    }

    @GetMapping("/get/product/{id}")
    public ResponseGetCartProduct getCartProduct(@PathVariable Long id) {
        return iCartService.getCartProduct(id);
    }

    @PatchMapping("/update")
    @Operation(summary = "장바구니 수정")
    void updateCart(@RequestBody RequestUpdateCart request){
        iCartService.updateCart(request);
    }

    @PatchMapping("/delete")
    @Operation(summary = "장바구니에서 선택 상품 삭제")
    void deleteCart(@RequestBody RequestDeleteCart request){
        iCartService.deleteCart(request.getCartId());
    }

    @PutMapping("/selectedDelete")
    @Operation(summary = "장바구니에서 선택 상품 여러개 삭제")
    void selectedDeleteCart(@RequestBody List<RequestDeleteSelectedCart> request) {
        iCartService.deleteSelectedCart(request);
    }

    @PutMapping("/delete/all")
    @Operation(summary = "장바구니 전체 삭제")
    void deleteAll(@RequestBody RequestDeleteAll request){
        iCartService.deleteAll(request.getUserId());
    }
}