package sphabucks.domain.event.service;

import sphabucks.domain.event.model.EventProductList;
import sphabucks.domain.products.vo.ResponseProductList;
import sphabucks.domain.products.vo.ResponseProductSummary;
import sphabucks.domain.event.vo.RequestEventProductList;

import java.util.List;

public interface IEventProductService {
    List<ResponseProductSummary> getProductsByEventId(Long eventId);
    List<ResponseProductList> recommendMD();

    // 이벤트상품리스트
    EventProductList addEventProductList(RequestEventProductList requestEventProductList);
    EventProductList getEventProductList(Long id);
}
