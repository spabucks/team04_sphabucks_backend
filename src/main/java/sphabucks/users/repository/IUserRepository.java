package sphabucks.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.users.model.User;
public interface IUserRepository extends JpaRepository<User, Long>{

}
