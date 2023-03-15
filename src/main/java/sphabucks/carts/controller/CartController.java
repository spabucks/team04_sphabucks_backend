package sphabucks.carts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.carts.service.ICartService;
import sphabucks.carts.vo.RequestCart;
import sphabucks.carts.vo.ResponseGetCart;
import sphabucks.carts.vo.ResponseGetCartProduct;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Tag(name = "장바구니(카트)")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
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

    @GetMapping("/get/product/{id}")
    public ResponseGetCartProduct getCartProduct(@PathVariable Long id) {
        return iCartService.getCartProduct(id);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "장바구니 수정")
    void updateCart(@PathVariable Long id, @RequestBody RequestCart requestCart){
        iCartService.updateCart(id, requestCart);
    }

    @GetMapping("/delete/{cartId}")
    @Operation(summary = "장바구니에서 선택 상품 삭제")
    void deleteCart(@PathVariable Long cartId){
        iCartService.deleteCart(cartId);
    }

    @PostMapping("/delete/all")
    @Operation(summary = "장바구니 전체 삭제")
    void deleteAll(@RequestBody String userId){
        iCartService.deleteAll(userId);
    }
}