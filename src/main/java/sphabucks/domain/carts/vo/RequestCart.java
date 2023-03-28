package sphabucks.domain.carts.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCart {
    private String userId;  // UUID
    private Long productId;
    private Long amount;
}
