package sphabucks.domain.purchaseHistory.service;

import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseHistoryList;
import sphabucks.global.auth.vo.RequestHead;

import java.util.List;

public interface IPurchaseHistoryService {

    void addPurchaseHistory(List<Long> selected, RequestHead requestHead);

    List<ResponsePurchaseHistoryList> getPurchaseHistoryList(RequestHead requestHead);


    //List<ResponsePurchaseHistoryList> getPurchaseHistory(String userId);
//
//    List<String> getAllPaymentNum (String userId);
//    List<PurchaseHistory> getAllByPaymentNum(String userId, String paymentNum);
}
