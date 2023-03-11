package sphabucks.carts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import sphabucks.carts.model.Cart;
import sphabucks.carts.service.ICartService;
import sphabucks.carts.vo.RequestCart;
import sphabucks.carts.vo.ResponseCart;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/add")
    void addCart(@RequestBody RequestCart requestCart){
        iCartService.addCart(requestCart);
    }

    @GetMapping("/get/{userId}")
    List<ResponseCart> getCart(@PathVariable String userId){
        return iCartService.getCart(userId);
    }

    @PostMapping("/update")
    void updateCart(@RequestBody RequestCart requestCart){
        iCartService.updateCart(requestCart);
    }

    @GetMapping("/delete/{cartId}")
    void deleteCart(@PathVariable Long cartId){
        iCartService.deleteCart(cartId);
    }

    @PostMapping("/delete/all")
    void deleteAll(@RequestBody String userId){
        iCartService.deleteAll(userId);
    }
}
