package sphabucks.products.vo;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class RequestProductCategoryList {

    @Column(nullable = false)
    private Integer bigCategoryId;
    @Column(nullable = false)
    private Integer smallCategoryId;
    @Column(nullable = false)
    private Long productId;

}
