package sphabucks.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.event.model.Event;
import sphabucks.event.model.EventImage;
import sphabucks.event.model.EventProductList;
import sphabucks.event.repository.IEventImageRepository;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.repository.IEventRepository;
import sphabucks.event.vo.RequestEventImage;
import sphabucks.products.model.Product;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final IEventRepository iEventRepository;
    private final IEventProductListRepository iEventProductListRepository;
    private final IEventImageRepository iEventImageRepository;


    @Override
    public Event addEvent(Event user) {
        return iEventRepository.save(user);
    }
    @Override
    public Event getEvent(Long id) {
        return iEventRepository.findById(id).get();
    }


    @Override
    public EventProductList addEventProductList(EventProductList eventProductList) {
        return iEventProductListRepository.save(eventProductList);
    }
    @Override
    public EventProductList getEventProductList(Long id) {
        return null;
    }


    @Override
    public EventImage addEventImage(RequestEventImage requestEventImage) {
        EventImage eventImage = EventImage.builder()
                .event(iEventRepository.findById(requestEventImage.getEventId()).get())
                .image(requestEventImage.getImage())
                .alt(requestEventImage.getAlt())
                .build();

        return iEventImageRepository.save(eventImage);
    }

    @Override
    public EventImage getEventImage(Long id) {
        return null;
    }


}
