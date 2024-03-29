package sphabucks.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sphabucks.domain.users.model.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUserId(String UUID);
    Optional<User> findByNameAndEmail(String name, String email);
    Boolean existsByLoginId(String loginId);

    Boolean existsByEmail(String email);


    Boolean existsByEmailAndLoginIdAndName(String email, String loginId, String name);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.pwd = :pwd WHERE u.loginId = :loginId")
    void resetPassword(@Param(value = "pwd") String pwd, @Param(value = "loginId") String loginId);


    Boolean existsByEmailAndName(String email, String name);

}

