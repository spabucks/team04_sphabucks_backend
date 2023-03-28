package sphabucks.domain.purchaseHistory.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ResponsePurchaseHistoryList {

    private Long id;
    private String orderName;
    private Long amount;
    private Long sum;
    private String paymentNum;
    private LocalDateTime orderDate;
    private String sp_status;
    private String or_status;
    private String image;
    private List<ResponsePurchaseHistory> list;



}
