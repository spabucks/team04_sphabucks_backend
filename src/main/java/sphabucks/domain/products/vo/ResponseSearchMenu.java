package sphabucks.domain.products.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ResponseSearchMenu {

    private List<Map<String, String>> bigCategory;
    private List<ResponseMenu> size;
    private List<ResponseMenu> price;
    private List<Map<String, String>> smallCategory;
    private List<ResponseMenu> season;

}
