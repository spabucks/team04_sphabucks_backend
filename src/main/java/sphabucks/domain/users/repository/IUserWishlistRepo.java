package sphabucks.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.users.model.UserWishlist;

import java.util.List;

public interface IUserWishlistRepo extends JpaRepository<UserWishlist, Long> {

    List<UserWishlist> findAllByUserUserIdAndIsDeletedIsFalse(String userId);

    boolean existsByUserUserIdAndProductId(String userId, Long productId);

    UserWishlist findByUserIdAndProductId(Long userId, Long productId);

    UserWishlist findByUserId(Long id);
}
