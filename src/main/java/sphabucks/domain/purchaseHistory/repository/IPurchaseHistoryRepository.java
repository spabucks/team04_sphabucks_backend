package sphabucks.domain.purchaseHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sphabucks.domain.purchaseHistory.model.PurchaseHistory;
import sphabucks.domain.purchaseHistory.vo.IResponsePaymentNum;

import java.util.List;

import java.util.Optional;

public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

    Optional<PurchaseHistory> findByPaymentNum(String paymentNum);

    @Query(value = "select payment_num as paymentNum, sum(sum) as sum, sum(amount) as amount " +
            "from purchase_history " +
            "where user_id = ? " +
            "group by payment_num", nativeQuery = true)
    List<IResponsePaymentNum> findAllPaymentNum(Long userId);

    @Query(value = "select * from purchase_history " +
            "where user_id = ? and payment_num = ?", nativeQuery = true)
    List<PurchaseHistory> findAllByPaymentNum(Long userId, String paymentNum);


}
