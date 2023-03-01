package sphabucks.event.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseRecommendMD {
    private Long productId;
    private String productName;
    private String imgUrl;
    private Integer productPrice;
    private Boolean isNew;
}