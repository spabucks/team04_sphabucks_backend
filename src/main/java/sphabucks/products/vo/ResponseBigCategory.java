package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseBigCategory {

    private Long index;
    private Long id;
    private String name;

}
