package sphabucks.purchaseHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.vo.IResponsePaymentNum;

import java.util.List;

import java.util.Optional;

public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

    Optional<PurchaseHistory> findByPaymentNum(String paymentNum);

    // 거래내역 조회 1. userId에 해당하는 모든 주문번호 조회 (중복x)
    @Query(value = "select payment_num as paymentNum, sum(sum) as sum, sum(amount) as amount " +
            "from purchase_history " +
            "where user_id = ? " +
            "group by payment_num", nativeQuery = true)
    List<IResponsePaymentNum> findAllPaymentNum(Long userId);

    @Query(value = "select * from purchase_history " +
            "where user_id = ? and payment_num = ?", nativeQuery = true)
    List<PurchaseHistory> findAllByPaymentNum(Long userId, String paymentNum);

    //List<PurchaseHistory> findAllByPaymentNumAndUserId(Long userId, String paymentNum);

}
