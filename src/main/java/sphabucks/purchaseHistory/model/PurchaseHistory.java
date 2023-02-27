package sphabucks.purchaseHistory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {

    @Id
    private Long id;
    private String category;
    private String product;
    private Long amount;
    private Long sum;
    private String payment_num;
    @ManyToOne
    private Long userId;
    private Boolean type;
    private String sp_status;
    private String or_status;

}
