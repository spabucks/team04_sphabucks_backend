package sphabucks.domain.purchaseHistory.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponsePurchaseTmp {

    private Long cartId;
    private Long productId;
    private Long count;
    private String productName;
    private String imgUrl;
    private Long price;
}
