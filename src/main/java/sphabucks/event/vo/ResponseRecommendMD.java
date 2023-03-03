package sphabucks.event.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseRecommendMD {
    private Long id;
    private String title;
    private String imgUrl;
    private Integer price;
    private Boolean isNew;
}