package sphabucks.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.event.model.EventImage;

import java.util.List;

public interface IEventImageRepository extends JpaRepository<EventImage, Long> {

    List<EventImage> findAll();
}
