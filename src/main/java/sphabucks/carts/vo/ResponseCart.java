package sphabucks.carts.vo;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseCart {

    private Long cartId;
    private Long productId;
    private Long bigCategoryId;
    private Integer count;
}
