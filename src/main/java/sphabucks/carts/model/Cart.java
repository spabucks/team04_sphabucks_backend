package sphabucks.carts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import sphabucks.products.model.Product;
import sphabucks.users.model.User;
import sphabucks.utility.BaseTimeEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private Long categoryId;
    private Long price;
    private String name;

    private Boolean isDelete;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

}
