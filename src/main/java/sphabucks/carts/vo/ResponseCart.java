package sphabucks.carts.vo;

import lombok.*;

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
