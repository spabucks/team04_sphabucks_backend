package sphabucks.purchaseHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.purchaseHistory.model.PurchaseHistory;

import java.util.Optional;

public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    Optional<PurchaseHistory> findByPayment_num(String paymentNum);
}
