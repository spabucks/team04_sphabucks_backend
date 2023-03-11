package sphabucks.products.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BigCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) // 대분류 카테고리명
    private String name;
}
