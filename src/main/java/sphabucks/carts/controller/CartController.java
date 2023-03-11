package sphabucks.carts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import sphabucks.carts.model.Cart;
import sphabucks.carts.service.ICartService;
import sphabucks.carts.vo.RequestCart;

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
    void addCart(@RequestBody RequestCart requestCart){
        iCartService.addCart(requestCart);
    }

    @GetMapping("/get/{userId}")
    @Operation(summary = "장바구니 조회", description = "uuid 사용")
    List<Cart> getCart(@PathVariable Long userId){
        return iCartService.getCart(userId);
    }

    @GetMapping("/update")
    @Operation(summary = "장바구니 수정")
    Cart updateCart(@RequestBody RequestCart requestCart){
        return iCartService.updateCart(requestCart);
    }

    @GetMapping("/delete")
    @Operation(summary = "장바구니에서 선택 상품 삭제")
    void deleteCart(@RequestBody RequestCart requestCart){
        iCartService.deleteCart(requestCart);
    }

    @GetMapping("/delete/all")
    @Operation(summary = "장바구니 전체 삭제")
    void deleteAll(@RequestBody RequestCart requestCart){
        iCartService.deleteAll(requestCart);
    }
}
