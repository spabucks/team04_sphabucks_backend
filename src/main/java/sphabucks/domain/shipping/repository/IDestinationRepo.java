package sphabucks.domain.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.shipping.model.Destination;

import java.util.List;
import java.util.Optional;

public interface IDestinationRepo extends JpaRepository<Destination, Long> {

    boolean existsByUserUserId(String UUID);

    Optional<Destination> findByUserIdAndDefaultDestinationIsTrue(Long userId);

    // 유저의 배송지 정보를 모두 조회한 후 기본 배송지를 최상단에 놓은 후 가장 최근에 변경한 순서대로 출력하기 위함
    List<Destination> findAllByUserIdOrderByDefaultDestinationDescUpdateDateDesc(Long userId);
}
