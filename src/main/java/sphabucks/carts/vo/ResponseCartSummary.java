package sphabucks.carts.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseCartSummary {

    private Long id;    // 장바구니 id

    private Long productId;
    private String productName;
    private String imgUrl;
    private Integer price;
    private Integer count;
}
