package sphabucks.carts.service;

import sphabucks.carts.model.Cart;
import sphabucks.carts.vo.RequestCart;

import java.util.List;

public interface ICartService {
    void addCart(RequestCart requestCart);
    List<Cart> getCart(Long id);
}
