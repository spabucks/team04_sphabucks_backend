package sphabucks.carts.vo;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCartV2 {

    // 카트의 상품을 한번에 모두 넘겨주는 api
    private Long cartId;
    private Long productId;
    private Long bigCategoryId;
    private Long count;
    private String productName;
    private String imgUrl;
    private Long price;
    private Boolean check;
}
