package sphabucks.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.users.model.UserLikes;

import java.util.List;

public interface IUserLikesRepo extends JpaRepository<UserLikes, Long> {
    List<UserLikes> findUserLikesByUserId(Long userId);
}
