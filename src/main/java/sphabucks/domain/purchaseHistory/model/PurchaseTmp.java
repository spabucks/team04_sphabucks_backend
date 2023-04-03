package sphabucks.domain.purchaseHistory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.domain.carts.model.Cart;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.users.model.User;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseTmp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @ManyToOne
    private Cart cart;


}
