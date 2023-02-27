package sphabucks.products.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BigCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
}
