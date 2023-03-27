package sphabucks.domain.products.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseBigCategory {

    private Long index;
    private Long id;
    private String name;

}
