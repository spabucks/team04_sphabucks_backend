package sphabucks.products.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestSearchParam {

    private String keyword;
    private Long bigCategory;
    private List<String> size;
    private List<Long> price;
    private List<Long> smallCategory;
    private List<Long> season;



}
