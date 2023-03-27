package sphabucks.domain.tag.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseRecommendTag {
    private Long id;
    private String name;
}
