package sphabucks.carts.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseGetCartProduct {
    private String productName;
    private Long price;
    private String imgUrl;
}
