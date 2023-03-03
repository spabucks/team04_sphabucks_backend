package sphabucks.purchaseHistory.vo;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class RequestPurchaseHistory {
    private String category;
    private String productName;
    private Long amount;
    private Long sum;
    private String payment_num;
    private Long userId;
    private Boolean type;
    private String sp_status;
    private String or_status;
    private String image;
}
