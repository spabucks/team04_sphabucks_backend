package sphabucks.carts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import sphabucks.products.model.Product;
import sphabucks.users.model.User;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    private Long categoryId;
    private Integer price;
    private String name;

    private Boolean isDelete = false;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;


}
