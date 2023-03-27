package sphabucks.domain.products.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseSearchResult {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private String size;
    private Long amount;
    private Boolean isBest;
    private Boolean isNew;
    private Long likeCount;
    private String imgUrl;
}
