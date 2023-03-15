package sphabucks.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sphabucks.users.model.UserLikes;

import java.util.List;

public interface IUserLikesRepo extends JpaRepository<UserLikes, Long> {
    List<UserLikes> findUserLikesByUserId(Long userId);
    Boolean existsAllByUserId(Long userId);

}
