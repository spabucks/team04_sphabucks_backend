package sphabucks.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.users.model.UserLikes;

import java.util.List;
import java.util.Optional;

public interface IUserLikesRepo extends JpaRepository<UserLikes, Long> {
    List<UserLikes> findAllByUserUserId(String userId);
//    Optional<UserLikes> findByUserUserId(String userId);
    Boolean existsAllByProductIdAndUserUserId(Long productId, String userId);
    void deleteByUserUserIdAndProductId(String userId, Long productId);

}
