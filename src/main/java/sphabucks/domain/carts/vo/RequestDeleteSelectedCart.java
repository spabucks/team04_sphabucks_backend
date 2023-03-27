package sphabucks.domain.carts.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeleteSelectedCart {

    private Long cartId;
    private Long count;
    private Long price;
    private Long bigCategoryId;
}
