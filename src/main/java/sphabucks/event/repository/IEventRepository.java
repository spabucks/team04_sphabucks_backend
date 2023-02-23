package sphabucks.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.event.model.Event;

public interface IEventRepository extends JpaRepository<Event, Long> {

}
