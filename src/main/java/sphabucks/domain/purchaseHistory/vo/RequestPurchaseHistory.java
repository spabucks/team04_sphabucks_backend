package sphabucks.domain.purchaseHistory.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestPurchaseHistory {
    private String productName;
    private Long amount;
    private Long sum;
    private String paymentNum;
    private Boolean type;
    private String sp_status;
    private String or_status;
    private String image;
}
