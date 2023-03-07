package sphabucks.products.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class ResponseSearchMenu {

    private List<ResponseMenu> bigCategory;
    private List<ResponseMenu> size;
    private List<ResponseMenu> price;
    private List<ResponseMenu> smallCategory;
    private List<ResponseMenu> season;

}
