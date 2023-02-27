package sphabucks.purchaseHistory.service;

import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.vo.RequestPurchaseHistory;

public interface IPurchaseHistoryService {

    PurchaseHistory addPurchaseHistory(RequestPurchaseHistory requestPurchaseHistory);

    PurchaseHistory getPurchaseHistory(Long id);
}
