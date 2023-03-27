package sphabucks.domain.event.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.domain.event.model.Event;
import sphabucks.domain.event.model.EventImage;
import sphabucks.domain.event.repository.IEventImageRepository;
import sphabucks.domain.event.repository.IEventProductListRepository;
import sphabucks.domain.event.repository.IEventRepository;
import sphabucks.domain.event.vo.RequestEvent;
import sphabucks.domain.event.vo.RequestEventImage;
import sphabucks.domain.event.vo.ResponseEventBanner;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.event.vo.*;
import sphabucks.domain.productimage.repository.IProductImageRepo;

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

        if(iEventRepository.findBySeason(requestEvent.getSeason()).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_EVENT, ErrorCode.DUPLICATE_EVENT.getCode());
        }

        ModelMapper modelMapper = new ModelMapper();
        Event event = modelMapper.map(requestEvent, Event.class);
        iEventRepository.save(event);
    }

    @Override
    public Event getEvent(Long id) {

        Event event = iEventRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.EVENT_NOT_EXISTS, ErrorCode.EVENT_NOT_EXISTS.getCode()));

        return event;
    }

    @Override
    public List<Event> getEventAll() {

        if(iEventRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.EVENT_NOT_EXISTS, ErrorCode.EVENT_NOT_EXISTS.getCode());
        }

        return iEventRepository.findAll();
    }

    @Override
    public EventImage addEventImage(RequestEventImage requestEventImage) {

        EventImage eventImage = EventImage.builder()
                .event(iEventRepository.findById(requestEventImage.getEventId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.DUPLICATE_EVENT, ErrorCode.DUPLICATE_EVENT.getCode())))
                .image(requestEventImage.getImage())
                .alt(requestEventImage.getAlt())
                .build();

        return iEventImageRepository.save(eventImage);
    }

    @Override
    public EventImage getEventImage(Long id) {

        return iEventImageRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.IMAGE_NOT_EXISTS, ErrorCode.IMAGE_NOT_EXISTS.getCode()));
    }

    @Override
    public List<Event> getRecommendEvent() {

        if(iEventRepository.findAllByIsRecommendIsTrue().isEmpty()){
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode());
        }

        return iEventRepository.findAllByIsRecommendIsTrue();
    }

    @Override
    public List<ResponseEventBanner> getEventBanner() {

        if(iEventImageRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS, ErrorCode.IMAGE_NOT_EXISTS.getCode());
        }
        List<EventImage> eventImages = iEventImageRepository.findAll();

        eventImages.sort((o1, o2) -> (int) (o2.getEvent().getId() - o1.getEvent().getId()));

        List<ResponseEventBanner> eventList = new ArrayList<>();

        eventImages.forEach(eventImage -> {
            if (eventImage.getEvent().getId() != 1) {
                if (eventImage.getEvent().getIsBanner()) {
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
