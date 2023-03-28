package sphabucks.domain.carts.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sphabucks.domain.carts.vo.*;

import java.util.List;

public interface ICartService {

    ResponseEntity<Object> addCart(RequestCart requestCart);

    // uuid 를 입력받음
    List<ResponseGetCart> getCart(String userId);

    // 카트안의 상품 정보를 불러오는 api
    ResponseGetCartProduct getCartProduct(Long productId);

    // 카트의 모든 상품 정보를 한번에 넘기는 api
    List<ResponseCartV2> getCartV2(String userId);

    void updateCart(RequestUpdateCart requestUpdateCart);

    void deleteCart(Long id);

    void deleteSelectedCart(List<RequestDeleteSelectedCart> requestList);

    void deleteAll(String userId);
}
