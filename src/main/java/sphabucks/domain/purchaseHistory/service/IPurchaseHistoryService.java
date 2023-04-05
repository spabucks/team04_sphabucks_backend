package sphabucks.domain.purchaseHistory.service;

import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseHistoryList;

import java.util.List;

public interface IPurchaseHistoryService {

    void addPurchaseHistory(String userId);

    List<ResponsePurchaseHistoryList> getPurchaseHistoryListAll(String userId);

    List<ResponsePurchaseHistoryList> getPurchaseHistoryListOne(String userId);

}
