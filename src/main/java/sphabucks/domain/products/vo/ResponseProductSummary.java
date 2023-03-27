package sphabucks.domain.products.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseProductSummary {
    private Long id;
    private String title;
    private String imgUrl;
    private Long price;
    private Boolean isNew;
    private Boolean isBest;
}