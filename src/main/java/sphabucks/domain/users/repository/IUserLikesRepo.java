package sphabucks.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.users.model.UserLikes;

import java.util.List;

public interface IUserLikesRepo extends JpaRepository<UserLikes, Long> {
    List<UserLikes> findUserLikesByUserUserId(String userId);
    Boolean existsAllByProductIdAndUserUserId(Long productId, String userId);
    void deleteByUserUserId(String userId);

}
