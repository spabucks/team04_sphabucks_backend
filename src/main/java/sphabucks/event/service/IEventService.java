package sphabucks.event.service;

import sphabucks.event.model.Event;
import sphabucks.event.model.EventImage;
import sphabucks.event.model.EventProductList;
import sphabucks.event.vo.RequestEventImage;
import sphabucks.event.vo.RequestEventProductList;
import sphabucks.products.model.Product;

public interface IEventService {

    Event addEvent(Event event);
    Event getEvent(Long id);

    // 이벤트상품리스트
    EventProductList addEventProductList(RequestEventProductList requestEventProductList);
    EventProductList getEventProductList(Long id);

    // 이벤트이미지
    EventImage addEventImage(RequestEventImage requestEventImage);
    EventImage getEventImage(Long id);

}
