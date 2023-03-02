package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseProduct {
    private Long productId;
    private String productThumbnailUrl;
    private String description;
    private Integer price;
    private List<String> productDetailImgUrl;
}
