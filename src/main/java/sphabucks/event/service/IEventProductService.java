package sphabucks.event.service;

import sphabucks.products.vo.ResponseProductList;
import sphabucks.products.vo.ResponseProductSummary;

import java.util.List;

public interface IEventProductService {
    List<ResponseProductSummary> getProductsByEventId(Long eventId);
    List<ResponseProductList> recommendMD();
}
