package sphabucks.carts.service;

import sphabucks.carts.model.Cart;
import sphabucks.carts.vo.RequestCart;
import sphabucks.carts.vo.ResponseCart;
import sphabucks.carts.vo.ResponseCartProduct;

import java.util.List;

public interface ICartService {
    void addCart(RequestCart requestCart);
    List<ResponseCart> getCart(String userId);  // uuid 를 입력받음
    ResponseCartProduct getCartProduct(Long productId);    // 카트안의 상품 정보를 불러오는 api

    void updateCart(RequestCart requestCart);

    void deleteCart(Long id);
    void deleteAll(String userId);

}
