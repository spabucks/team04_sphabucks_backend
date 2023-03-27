package sphabucks.domain.productimage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.domain.products.model.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String alt;
    private Long chk;

    @ManyToOne
    private Product product;
}
