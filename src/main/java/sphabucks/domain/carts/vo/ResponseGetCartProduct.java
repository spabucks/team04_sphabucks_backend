package sphabucks.domain.carts.vo;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetCartProduct {
    private String productName;
    private Long price;
    private String imgUrl;
}
