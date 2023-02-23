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
import sphabucks.event.vo.RequestEventProductList;
import sphabucks.products.repository.IProductRepository;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final IEventRepository iEventRepository;
    private final IEventProductListRepository iEventProductListRepository;
    private final IEventImageRepository iEventImageRepository;
    private final IProductRepository iProductRepository;


    @Override
    public Event addEvent(Event user) {
        return iEventRepository.save(user);
    }
    @Override
    public Event getEvent(Long id) {
        return iEventRepository.findById(id).get();
    }


    @Override
    public EventProductList addEventProductList(RequestEventProductList requestEventProductList) {
        EventProductList eventProductList = EventProductList.builder()
                .product(iProductRepository.findById(requestEventProductList.getProductId()).get())
                .event(iEventRepository.findById(requestEventProductList.getEventId()).get())
                .build();

        return iEventProductListRepository.save(eventProductList);
    }
    @Override
    public EventProductList getEventProductList(Long id) {
        return iEventProductListRepository.findById(id).get();
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

        return iEventImageRepository.findById(id).get();
    }


}
