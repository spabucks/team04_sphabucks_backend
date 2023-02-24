package sphabucks.tag.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.products.model.Product;
import sphabucks.tag.model.Tag;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTag {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name ="product")
    private Product product;
    @ManyToOne
    private Tag tag;

}
