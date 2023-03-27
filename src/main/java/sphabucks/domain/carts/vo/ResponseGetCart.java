package sphabucks.domain.carts.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseGetCart {

    // 카트(유저 - 상품 중간테이블) 정보를 넘겨주는 api
    private Long cartId;
    private Long productId;
    private Long bigCategoryId;
    private Long count;
}
