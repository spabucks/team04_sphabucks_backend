package sphabucks.carts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.carts.model.Cart;

import java.util.List;

public interface ICartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);
}
