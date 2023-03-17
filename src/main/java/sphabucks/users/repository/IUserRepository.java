package sphabucks.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.users.model.User;
import sphabucks.users.vo.ResponseUser;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUserId(String UUID);
    Boolean existsByLoginId(String loginId);

}

