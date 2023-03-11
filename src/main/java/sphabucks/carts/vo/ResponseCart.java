package sphabucks.carts.vo;

import lombok.*;
import org.springframework.stereotype.Service;
import sphabucks.carts.model.Cart;
import sphabucks.products.model.BigCategory;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseCart {
    private String categoryName;    // 냉동(케이크), 일반(그 외)
    private List<ResponseCartSummary> responseCartSummaryList;
}
