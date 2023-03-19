package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseCategoryMenu {

    private Long id;
    private String title;
    private List<ResponseMenu> data;


}