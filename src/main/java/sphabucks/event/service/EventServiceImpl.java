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

import java.util.ArrayList;
import java.util.List;

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
    public List<ResponseEventProduct> recommendMD() {
        List<ResponseEventProduct> responseEventProductList = new ArrayList<>();    // 최종 결과로 보내지는 리스트

        List<Event> recommendEvents = iEventRepository.findAllByIsRecommendIsTrue();    // 추천MD에 해당하는 이벤트 리스트
        recommendEvents.forEach( event -> {
            Long eventId = event.getId();
            List<EventProductList> eventProductLists = iEventProductListRepository.findAllByEvent_Id(eventId);  // 이벤트Id로 연관된 상품을 모두 가져옴
            List<ResponseRecommendMD> responseRecommendMDList = new ArrayList<>();  // 이벤트에 해당하는 상품 정보의 일부가 담길 리스트
            eventProductLists.forEach( eventProductList -> {    // 이벤트와 연결된 모든 상품에 대해서
                Long productId = eventProductList.getProduct().getId();
                responseRecommendMDList.add(ResponseRecommendMD.builder()
                        .productId(productId)   // 해당 상품의 id
                        .productName(eventProductList.getProduct().getName())   // 해당 상품의 이름
                        .imgUrl(iProductImageRepo.findByProductIdAndChkIsTrue(productId).getImage())    // 해당 상품의 썸네일(대표사진)
                        .productPrice(eventProductList.getProduct().getPrice()) // 해당 상품의 가격
                        .isNew(eventProductList.getProduct().getIsNew())    // 신상품 여부
                        .build());
            });
            responseEventProductList.add(ResponseEventProduct.builder() // 하나의 이벤트에 대한 내용과 상품들이 모두 담길 response
                    .eventId(eventId)
                    .eventName(iEventRepository.findById(eventId).get().getSeason())
                    .responseRecommendMDList(responseRecommendMDList)
                    .build());
        });
        return responseEventProductList;
    }

    @Override
    public List<Event> getRecommendEvent() {
        return iEventRepository.findAllByIsRecommendIsTrue();
    }
}
