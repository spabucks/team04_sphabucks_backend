package sphabucks.domain.carts.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCart {
    private String userId;  // UUID
    private Long productId;
    private Long amount;
}
