package sphabucks.carts.controller;

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
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/add")
    Integer addCart(@RequestBody RequestCart requestCart){
        return iCartService.addCart(requestCart);
    }

    @GetMapping("/get/{userId}")
    List<ResponseGetCart> getCart(@PathVariable String userId){
        return iCartService.getCart(userId);
    }

    @GetMapping("/get/product/{id}")
    public ResponseGetCartProduct getCartProduct(@PathVariable Long id) {
        return iCartService.getCartProduct(id);
    }

    @PutMapping("/update/{id}")
    void updateCart(@PathVariable Long id, @RequestBody String amount){
        iCartService.updateCart(id, Integer.parseInt(amount));
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
