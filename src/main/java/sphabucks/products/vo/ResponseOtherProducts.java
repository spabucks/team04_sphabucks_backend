package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseOtherProducts {
    private Long id;
    private String name;
    private String imgUrl;
    private Integer price;
}
