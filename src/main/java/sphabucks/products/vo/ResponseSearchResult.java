package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseSearchResult {
    Long id;
    String name;
    String description;
    Long price;
    String size;
    Long amount;
    Boolean isBest;
    Boolean isNew;
    Long likeCount;
    String imgUrl;
}
