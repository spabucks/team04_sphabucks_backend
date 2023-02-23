package sphabucks.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.event.model.EventImage;

public interface IEventImageRepository extends JpaRepository<EventImage, Long> {

}
