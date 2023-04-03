package sphabucks.domain.purchaseHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sphabucks.domain.purchaseHistory.model.PurchaseTmp;

import java.util.List;
import java.util.Optional;

public interface IPurchaseTmpRepository extends JpaRepository<PurchaseTmp, Long> {

    Optional<PurchaseTmp> findByCartIdAndUserId(Long cartId, String userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from purchase_tmp WHERE user_id = :userId", nativeQuery = true)
    void deletePurchaseTmp(@Param(value="userId") String userId);

    List<PurchaseTmp> findByUserId(String userId);

    List<PurchaseTmp> findAllByUserId(String userId);
}
