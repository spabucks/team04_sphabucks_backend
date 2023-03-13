package sphabucks.carts.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseGetCart {

    private Long cartId;
    private Long productId;
    private Long bigCategoryId;
    private Integer count;
}
