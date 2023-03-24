package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseCategoryMenu {

    private Long index;
    private String title;
    private String value;
    private List<ResponseMenu> data;


}
