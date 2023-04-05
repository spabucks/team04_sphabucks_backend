package sphabucks.domain.purchaseHistory.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestPurchaseTmp {

    private List<Long> cartId;
}
