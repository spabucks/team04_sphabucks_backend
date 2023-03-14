package sphabucks.event.service;

import sphabucks.event.model.EventProductList;
import sphabucks.event.vo.RequestEventProductList;
import sphabucks.products.vo.ResponseProductList;
import sphabucks.products.vo.ResponseProductSummary;

import java.util.List;

public interface IEventProductService {
    List<ResponseProductSummary> getProductsByEventId(Long eventId);
    List<ResponseProductList> recommendMD();

    // 이벤트상품리스트
    EventProductList addEventProductList(RequestEventProductList requestEventProductList);
    EventProductList getEventProductList(Long id);
}
