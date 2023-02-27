package sphabucks.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.users.model.UserWishlist;
import sphabucks.users.vo.RequestUserWishlist;

import java.util.List;

public interface IUserWishlistRepo extends JpaRepository<UserWishlist, Long> {
    List<UserWishlist> findAllByUserId(Long userId);
}
