package sphabucks.domain.carts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.carts.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartRepo extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserUserIdAndIsDeleteIsFalse(String userId);

    List<Cart> findAllByProductId(Long productId);

    Optional<Cart> findByUserUserIdAndProductId(String userId, Long productId);

    // 조회하는 유저의 장바구니에 해당 상품이 담겼던 이력이 있는지를 판단하는 쿼리문(과거에 담겼던 제품 포함)
    boolean existsByUserUserIdAndProductId(String userId, Long productId);

    Optional<Cart> findByIdAndUserUserId(Long id, String userId);
}
