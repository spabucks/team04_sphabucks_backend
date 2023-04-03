package sphabucks.domain.purchaseHistory.vo;

import lombok.Data;

import java.util.List;

@Data
public class RequestPurchaseTmp {

    private List<Long> cartId;
}
