package sphabucks.purchaseHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.purchaseHistory.model.PurchaseHistory;

public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}
