package sphabucks.users.vo;

import lombok.Getter;

@Getter
public class RequestUserWishlist {
    private Long userId;
    private Long productId;
}
