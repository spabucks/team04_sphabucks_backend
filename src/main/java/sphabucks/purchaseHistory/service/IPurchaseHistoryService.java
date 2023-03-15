package sphabucks.purchaseHistory.service;

import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.vo.RequestPurchaseHistory;

import java.util.List;

public interface IPurchaseHistoryService {

    void addPurchaseHistory(List<Long> selected, String userId);

    PurchaseHistory getPurchaseHistory(Long id);
}
