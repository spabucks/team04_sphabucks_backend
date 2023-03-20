package sphabucks.carts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.carts.service.ICartService;
import sphabucks.carts.vo.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "장바구니(카트)")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Slf4j
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/v1/add")
    @Operation(summary = "장바구니 담기")
    Long addCart(@RequestBody RequestCart requestCart){
        return iCartService.addCart(requestCart);
    }

    @GetMapping("/v1/get/{userId}")
    @Operation(summary = "장바구니 조회", description = "uuid 사용")
    List<ResponseGetCart> getCart(@PathVariable String userId){
        return iCartService.getCart(userId);
    }

    @GetMapping("/v1/get/product/{id}")
    public ResponseGetCartProduct getCartProduct(@PathVariable Long id) {
        return iCartService.getCartProduct(id);
    }

    @PatchMapping("/v1/update")
    @Operation(summary = "장바구니 수정")
    void updateCart(@RequestBody RequestUpdateCart request){
        iCartService.updateCart(request);
    }

    @PatchMapping("/v1/delete")
    @Operation(summary = "장바구니에서 선택 상품 삭제")
    void deleteCart(@RequestBody RequestDeleteCart request){
        iCartService.deleteCart(request.getCartId());
    }

    @PutMapping("/v1/selectedDelete")
    @Operation(summary = "장바구니에서 선택 상품 여러개 삭제")
    void selectedDeleteCart(@RequestBody List<RequestDeleteSelectedCart> request) {
        iCartService.deleteSelectedCart(request);
    }

    @PutMapping("/v1/delete/all")
    @Operation(summary = "장바구니 전체 삭제")
    void deleteAll(@RequestBody RequestDeleteAll request){
        iCartService.deleteAll(request.getUserId());
    }
}