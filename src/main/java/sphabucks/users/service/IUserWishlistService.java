package sphabucks.users.service;

import sphabucks.users.model.UserWishlist;
import sphabucks.users.vo.RequestUserWishlist;

import java.util.List;

public interface IUserWishlistService {

    void addUserWishlist(RequestUserWishlist requestUserWishlist);
    List<UserWishlist> getByUserWishlist(Long userId);
}
