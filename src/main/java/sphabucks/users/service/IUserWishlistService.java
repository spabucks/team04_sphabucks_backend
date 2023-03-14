package sphabucks.users.service;

import sphabucks.users.model.UserWishlist;
import sphabucks.users.vo.RequestUserWishlist;

import java.util.List;

public interface IUserWishlistService {

    // 위시리스트에 추가가 안되어 있을 때 추가하고 추가되어 있을 때 삭제하는 api
    void clickWishList(RequestUserWishlist requestUserWishlist);
    List<UserWishlist> getByUserWishlist(Long userId);
}
