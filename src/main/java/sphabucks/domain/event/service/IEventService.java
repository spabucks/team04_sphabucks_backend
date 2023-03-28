package sphabucks.domain.event.service;

import sphabucks.domain.event.model.EventImage;
import sphabucks.domain.event.vo.RequestEvent;
import sphabucks.domain.event.vo.RequestEventImage;
import sphabucks.domain.event.model.Event;
import sphabucks.domain.event.vo.ResponseEventBanner;

import java.util.List;

public interface IEventService {

    // 이벤트
    void addEvent(RequestEvent requestEvent);
    Event getEvent(Long id);
    List<Event> getEventAll();

    // 이벤트이미지
    EventImage addEventImage(RequestEventImage requestEventImage);
    EventImage getEventImage(Long id);
    List<Event> getRecommendEvent();

    // 이벤트 배너 이미지
    List<ResponseEventBanner> getEventBanner();

}
