package sphabucks.products.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseSearchProduct {

    private Long productId;
    private String bigCategory;
    private String smallCategory;
    private String event;
    private String tag;
    private String productName;
    private String imgUrl;
    private Long price;
    private String size;
    private Long amount;
    private Boolean isNew;
    private Boolean isBest;
    private Long likeCount;


}
