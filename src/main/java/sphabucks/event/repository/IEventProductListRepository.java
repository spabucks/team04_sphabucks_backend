package sphabucks.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.event.model.EventProductList;

public interface IEventProductListRepository extends JpaRepository<EventProductList, Long> {
}
