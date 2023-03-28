package sphabucks.domain.products.vo;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class RequestSmallCategory {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long bigCategoryId;
}
