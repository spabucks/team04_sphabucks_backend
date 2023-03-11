package sphabucks.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.event.model.Event;
import sphabucks.event.model.EventProductList;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.repository.IEventRepository;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.model.ProductCategoryList;
import sphabucks.products.repository.IProductRepository;
import sphabucks.products.vo.ResponseProductList;
import sphabucks.products.vo.ResponseProductSummary;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventProductServiceImpl implements IEventProductService{
    private final IEventRepository iEventRepository;
    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;
    private final IEventProductListRepository iEventProductListRepository;
    @Override
    public List<ResponseProductSummary> getProductsByEventId(Long eventId) {
        List<ResponseProductSummary> responseProductSummaries = new ArrayList<>();
        List<EventProductList> eventProductLists = iEventProductListRepository.findAllByEvent_Id(eventId);
        for (EventProductList eventProductList : eventProductLists) {
            Product product = iProductRepository.findById(eventProductList.getProduct().getId()).get();
            responseProductSummaries.add(ResponseProductSummary.builder()
                    .id(product.getId())
                    .title(product.getName())
                    .price(product.getPrice())
                    .isNew(product.getIsNew())
                    .imgUrl(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
                    .build());
        }
        return responseProductSummaries;
    }

    @Override
    public List<ResponseProductList> recommendMD() {
        List<ResponseProductList> responseProductLists = new ArrayList<>();    // 최종 결과로 보내지는 리스트

        List<Event> recommendEvents = iEventRepository.findAllByIsRecommendIsTrue();    // 추천MD에 해당하는 이벤트 리스트
        recommendEvents.forEach( event -> {
            Long eventId = event.getId();
            responseProductLists.add(ResponseProductList.builder() // 하나의 이벤트에 대한 내용과 상품들이 모두 담길 response
                    .id(eventId)
                    .name(iEventRepository.findById(eventId).get().getSeason())
                    .data(getProductsByEventId(eventId))
                    .build());
        });
        return responseProductLists;
    }
}
