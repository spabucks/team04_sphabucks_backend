package sphabucks.purchaseHistory.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResponsePurchaseHistory {
    private Long id;
    private String productName;
    private Long amount;
    private Long sum;
    private Boolean type;
    private String paymentNum;
    private LocalDateTime orderDate;
    private String sp_status;
    private String or_status;
    private String image;
}
