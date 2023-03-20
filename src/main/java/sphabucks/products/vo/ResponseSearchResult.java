package sphabucks.products.vo;

import lombok.Getter;

@Getter
public class ResponseSearchResult {
    Long id;
    String name;
    String price;
    String size;
    String image;
    Long eventId;
    Long bigCategoryId;
    Long smallCategoryId;
}
