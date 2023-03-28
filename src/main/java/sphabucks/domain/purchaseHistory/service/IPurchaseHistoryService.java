package sphabucks.domain.purchaseHistory.service;

import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseHistoryList;

import java.util.List;

public interface IPurchaseHistoryService {

    void addPurchaseHistory(List<Long> selected, String userId);

    List<ResponsePurchaseHistoryList> getPurchaseHistoryList(String userId);


    //List<ResponsePurchaseHistoryList> getPurchaseHistory(String userId);
//
//    List<String> getAllPaymentNum (String userId);
//    List<PurchaseHistory> getAllByPaymentNum(String userId, String paymentNum);
}
