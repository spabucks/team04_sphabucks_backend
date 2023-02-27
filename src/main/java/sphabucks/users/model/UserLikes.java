package sphabucks.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.products.model.Product;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}
