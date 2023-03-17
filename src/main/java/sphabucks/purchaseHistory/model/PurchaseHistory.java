package sphabucks.purchaseHistory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.users.model.User;
import sphabucks.utility.BaseTimeEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private Long sum;
    @Column(nullable = false)
    private String paymentNum;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private Boolean type;
    @Column(nullable = false)
    private String spStatus;
    @Column(nullable = false)
    private String orStatus;
    @Column(nullable = false)
    private String image;

}
