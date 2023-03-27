package sphabucks.domain.users.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserLikes {
    private String userId;
    private Long productId;
}
