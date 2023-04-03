package sphabucks.domain.purchaseHistory.service;

import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseHistoryList;

import java.util.List;

public interface IPurchaseHistoryService {

    void addPurchaseHistory(List<Long> selected, String userId);

    List<ResponsePurchaseHistoryList> getPurchaseHistoryList(String userId);

}
