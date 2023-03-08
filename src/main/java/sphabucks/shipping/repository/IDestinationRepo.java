package sphabucks.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.shipping.model.Destination;

public interface IDestinationRepo extends JpaRepository<Destination, Long> {

    boolean existsByUserUserId(String UUID);    // 기존에 등록되있는 배송지가 있는지
    Destination findByUserIdAndDefaultDestinationIsTrue(Long userId);
}
