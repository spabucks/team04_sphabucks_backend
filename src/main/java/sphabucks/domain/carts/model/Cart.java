package sphabucks.domain.carts.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.users.model.User;
import sphabucks.global.utility.BaseTimeEntity;

@Entity
@Getter
@Setter
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
