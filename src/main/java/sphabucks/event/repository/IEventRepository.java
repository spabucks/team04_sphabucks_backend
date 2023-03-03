package sphabucks.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.event.model.Event;

import java.util.List;

public interface IEventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByIsRecommendIsTrue();   // 추천 MD에 띄우는 이벤트 정보를 받아오는 쿼리
}
