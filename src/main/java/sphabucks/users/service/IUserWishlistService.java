package sphabucks.users.service;

import sphabucks.users.model.UserWishlist;
import sphabucks.users.vo.RequestUserWishlist;
import sphabucks.users.vo.ResponseWishList;
import sphabucks.users.vo.ResponseWishListProduct;

import java.util.List;

public interface IUserWishlistService {

    // 위시리스트에 추가가 안되어 있을 때 추가하고 추가되어 있을 때 삭제하는 api
    void clickWishList(RequestUserWishlist requestUserWishlist);

    // 위시리스트 중간테이블 반환
    List<ResponseWishList> getByUserWishlist(String userId);

    // 반환된 중간테이블의 정보를 바탕으로 해당 상품의 정보 반환
    ResponseWishListProduct getWishListProduct(Long productId);
}
