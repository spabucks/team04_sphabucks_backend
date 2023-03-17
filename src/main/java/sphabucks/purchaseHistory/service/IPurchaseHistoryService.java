package sphabucks.purchaseHistory.service;

import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.vo.RequestPurchaseHistory;
import sphabucks.purchaseHistory.vo.ResponsePurchaseHistory;
import sphabucks.purchaseHistory.vo.ResponsePurchaseHistoryList;

import java.util.List;

public interface IPurchaseHistoryService {

    void addPurchaseHistory(List<Long> selected, String userId);

    List<ResponsePurchaseHistoryList> getPurchaseHistoryList(String userId);


    //List<ResponsePurchaseHistoryList> getPurchaseHistory(String userId);
//
//    List<String> getAllPaymentNum (String userId);
//    List<PurchaseHistory> getAllByPaymentNum(String userId, String paymentNum);
}
