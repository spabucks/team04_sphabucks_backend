package sphabucks.carts.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseCartProduct {
    private String productName;
    private Integer price;
    private String imgUrl;
}
