package sphabucks.carts.controller;

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
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/add")
    void addCart(@RequestBody RequestCart requestCart){
        iCartService.addCart(requestCart);
    }

    @GetMapping("/get/{userId}")
    List<Cart> getCart(@PathVariable Long userId){
        return iCartService.getCart(userId);
    }

    @GetMapping("/update")
    Cart updateCart(@RequestBody RequestCart requestCart){
        return iCartService.updateCart(requestCart);
    }

    @GetMapping("/delete")
    void deleteCart(@RequestBody RequestCart requestCart){
        iCartService.deleteCart(requestCart);
    }

    @GetMapping("/delete/all")
    void deleteAll(@RequestBody RequestCart requestCart){
        iCartService.deleteAll(requestCart);
    }
}
