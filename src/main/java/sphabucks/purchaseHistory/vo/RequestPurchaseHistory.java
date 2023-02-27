package sphabucks.purchaseHistory.vo;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class RequestPurchaseHistory {

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String product;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private Long sum;
    @Column(nullable = false)
    private String payment_num;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Boolean type;
    @Column(nullable = false)
    private String sp_status;
    @Column(nullable = false)
    private String or_status;
}
