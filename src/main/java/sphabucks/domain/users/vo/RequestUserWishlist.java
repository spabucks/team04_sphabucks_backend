package sphabucks.domain.users.vo;

import lombok.Getter;

@Getter
public class RequestUserWishlist {

    private String userId;
    private Long productId;
}
