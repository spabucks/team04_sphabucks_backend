package sphabucks.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.event.model.EventProductList;
import sphabucks.products.model.Product;

import java.util.List;
import java.util.Optional;

public interface IEventProductListRepository extends JpaRepository<EventProductList, Long> {
    List<EventProductList> findAllByEvent_Id(Long id);  // 이벤트 id를 통해 상품들(리스트) 찾기
    Optional<EventProductList> findByProductId(Long id);  // 상품을 통해 이벤트 찾기

}
