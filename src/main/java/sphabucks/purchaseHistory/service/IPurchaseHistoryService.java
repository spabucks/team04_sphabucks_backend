package sphabucks.purchaseHistory.service;

import sphabucks.purchaseHistory.model.PurchaseHistory;

public interface IPurchaseHistoryService {

    PurchaseHistory addPurchaseHistory(PurchaseHistory purchaseHistory);

    PurchaseHistory getPurchaseHistory(Long id);
}
