package sphabucks.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.users.model.UserWishlist;

import java.util.List;

public interface IUserWishlistRepo extends JpaRepository<UserWishlist, Long> {

    // user 의 위시리스트 중에서 isDeleted = false 인 것들만 조회(위시리스트에 등록된 상품들)
    List<UserWishlist> findAllByUserUserIdAndIsDeletedIsFalse(String userId);

    boolean existsByUserUserIdAndProductId(String userId, Long productId);

    UserWishlist findByUserIdAndProductId(Long userId, Long productId);

    UserWishlist findByUserId(Long id);
}
