package sphabucks.carts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sphabucks.carts.model.Cart;

import java.util.List;

public interface ICartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);

    List<Cart> findAllByUserUserIdAndIsDeleteIsFalse(String userId);    // 고객의 장바구니에서 남아있는 상품들에 대한 정보만 조회
    boolean existsByProductId(Long productId);
    List<Cart> findAllByProductId(Long productId);
    Cart findByUserUserIdAndProductId(String userId, Long productId);

    // 조회하는 유저의 장바구니에 해당 상품이 담겼던 이력이 있는지를 판단하는 쿼리문(과거에 담겼던 제품 포함)
    boolean existsByUserUserIdAndProductId(String userId, Long productId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Cart c SET c.amount = :amount where c.product.id = :id ")
    void updateAmount(@Param(value="amount") Long amount, @Param(value="id") Long id);
}
