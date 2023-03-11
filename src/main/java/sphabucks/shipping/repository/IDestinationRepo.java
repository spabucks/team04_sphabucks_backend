package sphabucks.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.shipping.model.Destination;

import java.util.List;

public interface IDestinationRepo extends JpaRepository<Destination, Long> {

    boolean existsByUserUserId(String UUID);    // 기존에 등록되있는 배송지가 있는지

    Destination findByUserIdAndDefaultDestinationIsTrue(Long userId);   // 사용자의 배송지 중 기본 배송지 정보 조회

    // 유저의 배송지 정보를 모두 조회한 후 기본 배송지를 최상단에 놓은 후 가장 최근에 변경한 순서대로 출력하기 위함
    List<Destination> findAllByUserIdOrderByDefaultDestinationDescUpdateDateDesc(Long userId);
}
