package sphabucks.domain.carts.vo;

import lombok.Getter;

@Getter
public class RequestCart {
    private String userId;  // UUID
    private Long productId;
    private Long amount;
}
