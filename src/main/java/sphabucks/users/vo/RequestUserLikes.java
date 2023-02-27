package sphabucks.users.vo;

import lombok.Getter;

@Getter
public class RequestUserLikes {
    private Long userId;
    private Long productId;
}
