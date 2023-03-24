package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseProduct {
    private Long id;
    private String title;
    private String imgUrl;
    private String description;
    private Long price;
    private List<String> productDetailImgUrl;
    private Long likeCount;
}
