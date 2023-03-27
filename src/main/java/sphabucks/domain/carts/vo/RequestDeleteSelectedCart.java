package sphabucks.domain.carts.vo;

import lombok.Getter;

@Getter
public class RequestDeleteSelectedCart {

    private Long cartId;
    private Long count;
    private Long price;
    private Long bigCategoryId;
}
