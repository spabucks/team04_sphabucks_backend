package sphabucks.event.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.products.model.Product;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Event event;


}
