package sphabucks.products.vo;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class RequestBigCategory {
    @Column(nullable = false)
    private String name;
}
