package sphabucks.event.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.event.model.Event;
import sphabucks.event.model.EventImage;
import sphabucks.event.model.EventProductList;
import sphabucks.event.repository.IEventImageRepository;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.repository.IEventRepository;
import sphabucks.event.vo.*;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.vo.ResponseProductList;
import sphabucks.products.vo.ResponseProductSummary;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final IEventRepository iEventRepository;
    private final IEventProductListRepository iEventProductListRepository;
    private final IEventImageRepository iEventImageRepository;
    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;


    @Override
    public void addEvent(RequestEvent requestEvent) {
        ModelMapper modelMapper = new ModelMapper();
        Event event = modelMapper.map(requestEvent, Event.class);
        iEventRepository.save(event);
    }

    @Override
    public Event getEvent(Long id) {
        return iEventRepository.findById(id).get();
    }

    @Override
    public List<Event> getEventAll() {
        return iEventRepository.findAll();
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

    @Override
    public List<Event> getRecommendEvent() {
        return iEventRepository.findAllByIsRecommendIsTrue();
    }

    @Override
    public List<ResponseEventBanner> getEventBanner() {
        List<EventImage> eventImages = iEventImageRepository.findAll();
        Collections.sort(eventImages, (o1, o2) -> (int) (o2.getEvent().getId() - o1.getEvent().getId()));

        List<ResponseEventBanner> eventList = new ArrayList<>();

        eventImages.forEach(eventImage -> {
            if (eventImage.getEvent().getId() != 1) {
                if (eventImage.getEvent().getIsRecommend() == false) {
                    ResponseEventBanner responseEventBanner = ResponseEventBanner.builder()
                            .eventId(eventImage.getEvent().getId())
                            .name(eventImage.getEvent().getSeason())
                            .imageUrl(eventImage.getImage())
                            .build();

                    eventList.add(responseEventBanner);
                }
            }
        });

        return eventList;
    }
}
