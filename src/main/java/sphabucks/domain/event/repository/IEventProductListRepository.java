package sphabucks.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.event.model.EventProductList;

import java.util.List;
import java.util.Optional;

public interface IEventProductListRepository extends JpaRepository<EventProductList, Long> {
    List<EventProductList> findAllByEvent_Id(Long id);
    Optional<EventProductList> findByProductId(Long id);

}
