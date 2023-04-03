package sphabucks.domain.users.service;

import sphabucks.domain.users.vo.RequestUserWishlist;
import sphabucks.domain.users.vo.ResponseWishList;
import sphabucks.domain.users.vo.ResponseWishListProduct;

import java.util.List;

public interface IUserWishlistService {

    void clickWishList(String userId, RequestUserWishlist requestUserWishlist);

    List<ResponseWishList> getByUserWishlist(String userId);

    ResponseWishListProduct getWishListProduct(Long productId);
}
