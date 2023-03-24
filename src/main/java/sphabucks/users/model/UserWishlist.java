package sphabucks.users.model;

import jakarta.persistence.*;
import lombok.*;
import sphabucks.products.model.Product;
import sphabucks.utility.BaseTimeEntity;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWishlist extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Boolean isDeleted;  // true : 위시리스트에서 삭제된 상품, false : 위시리스트에 담겨있는 상품

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

}
