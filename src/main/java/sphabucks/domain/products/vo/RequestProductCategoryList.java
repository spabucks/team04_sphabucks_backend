package sphabucks.domain.products.vo;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class RequestProductCategoryList {

    private Long bigCategoryId;
    private Long smallCategoryId;
    private Long productId;

}
