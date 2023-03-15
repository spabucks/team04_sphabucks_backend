package sphabucks.carts.service;

import sphabucks.carts.vo.RequestCart;
import sphabucks.carts.vo.ResponseGetCart;
import sphabucks.carts.vo.ResponseGetCartProduct;

import java.util.List;

public interface ICartService {
    Long addCart(RequestCart requestCart);
    List<ResponseGetCart> getCart(String userId);  // uuid 를 입력받음
    ResponseGetCartProduct getCartProduct(Long productId);    // 카트안의 상품 정보를 불러오는 api

    void updateCart(Long productId, RequestCart requestCart);

    void deleteCart(Long id);
    void deleteAll(String userId);

}
