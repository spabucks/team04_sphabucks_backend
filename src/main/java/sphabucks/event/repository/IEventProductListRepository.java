package sphabucks.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.event.model.EventProductList;
import sphabucks.products.model.Product;

import java.util.List;

public interface IEventProductListRepository extends JpaRepository<EventProductList, Long> {
    List<EventProductList> findAllByEvent_Id(Long id);
}
