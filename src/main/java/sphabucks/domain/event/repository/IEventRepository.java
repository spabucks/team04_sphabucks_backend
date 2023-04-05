package sphabucks.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.event.model.Event;

import java.util.List;
import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByIsRecommendIsTrue();
    Optional<Event> findBySeason(String season);
}
