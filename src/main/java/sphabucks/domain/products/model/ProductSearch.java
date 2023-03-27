package sphabucks.domain.products.model;

import jakarta.persistence.*;
import lombok.Data;
import sphabucks.domain.event.model.Event;

@Entity
@Data
public class ProductSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    @ManyToOne
    private BigCategory bigCategory;
    @ManyToOne
    private SmallCategory smallCategory;
    @ManyToOne
    private Event event;
}
