package sphabucks.carts.service;

import sphabucks.carts.vo.*;

import java.util.List;

public interface ICartService {
    Long addCart(RequestCart requestCart);
    List<ResponseGetCart> getCart(String userId);  // uuid 를 입력받음
    ResponseGetCartProduct getCartProduct(Long productId);    // 카트안의 상품 정보를 불러오는 api

    void updateCart(RequestUpdateCart requestUpdateCart);

    void deleteCart(Long id);
    void deleteSelectedCart(List<RequestDeleteSelectedCart> requestList);
    void deleteAll(String userId);
}
