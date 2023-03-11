package sphabucks.carts.vo;

import lombok.Getter;

@Getter
public class RequestCart {
    private String userId;
    private Long productId;
    private Long amount;
}
