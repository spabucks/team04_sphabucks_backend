package sphabucks.event.service;

import sphabucks.event.model.Event;
import sphabucks.event.model.EventImage;
import sphabucks.event.model.EventProductList;
import sphabucks.event.vo.RequestEvent;
import sphabucks.event.vo.RequestEventImage;
import sphabucks.event.vo.RequestEventProductList;
import sphabucks.products.vo.ResponseProductList;

import java.util.List;

public interface IEventService {

    // 이벤트
    void addEvent(RequestEvent requestEvent);
    Event getEvent(Long id);
    List<Event> getEventAll();

    // 이벤트상품리스트
    EventProductList addEventProductList(RequestEventProductList requestEventProductList);
    EventProductList getEventProductList(Long id);

    // 이벤트이미지
    EventImage addEventImage(RequestEventImage requestEventImage);
    EventImage getEventImage(Long id);
    List<Event> getRecommendEvent();
}
