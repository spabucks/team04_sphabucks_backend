package sphabucks.purchaseHistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.repository.IPurchaseHistoryRepository;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryServiceImpl implements IPurchaseHistoryService{

    private final IPurchaseHistoryRepository iPurchaseHistoryRepository;

    @Override
    public PurchaseHistory addPurchaseHistory(PurchaseHistory purchaseHistory) {
        return iPurchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public PurchaseHistory getPurchaseHistory(Long id) {
        return iPurchaseHistoryRepository.findById(id).get();
    }
}
