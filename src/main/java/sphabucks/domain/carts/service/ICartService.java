package sphabucks.domain.carts.service;

import sphabucks.domain.carts.vo.*;

import java.util.List;

public interface ICartService {

    Long addCart(RequestCart requestCart);

    // uuid 를 입력받음
    List<ResponseGetCart> getCart(String userId);

    // 카트안의 상품 정보를 불러오는 api
    ResponseGetCartProduct getCartProduct(Long productId);

    // 카트의 모든 상품 정보를 한번에 넘기는 api
    List<ResponseCartV2> getCartV2(String userId);

    Boolean updateCart(RequestUpdateCart requestUpdateCart);

    Boolean deleteCart(Long id);

    Boolean deleteSelectedCart(List<RequestDeleteSelectedCart> requestList);

    Boolean deleteAll(String userId);

}
