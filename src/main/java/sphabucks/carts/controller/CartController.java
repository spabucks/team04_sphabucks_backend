package sphabucks.carts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.carts.model.Cart;
import sphabucks.carts.service.ICartService;
import sphabucks.carts.vo.RequestCart;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/cart")
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/add")
    void addCart(@RequestBody RequestCart requestCart){
        iCartService.addCart(requestCart);
    }

    @GetMapping("/get/{userId}")
    List<Cart> getCart(Long userId){
        return iCartService.getCart(userId);
    }
}
