package sphabucks.purchaseHistory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.users.model.User;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private Long sum;
    @Column(nullable = false)
    private String payment_num;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private Boolean type;
    @Column(nullable = false)
    private String sp_status;
    @Column(nullable = false)
    private String or_status;
    @Column(nullable = false)
    private String image;

}
