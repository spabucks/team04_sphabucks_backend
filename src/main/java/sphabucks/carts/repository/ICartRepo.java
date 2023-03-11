package sphabucks.carts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.carts.model.Cart;

import java.util.List;

public interface ICartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);

    List<Cart> findAllByUserUserIdAndIsDeleteIsFalse(String userId);    // 고객의 장바구니에서 남아있는 상품들에 대한 정보만 조회
    boolean existsByProductId(Long productId);
    List<Cart> findAllByProductId(Long productId);
    List<Cart> findAllByProductIdAndUserId(Long productId, Long userId);
}
