package sphabucks.purchaseHistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.repository.IPurchaseHistoryRepository;
import sphabucks.purchaseHistory.vo.RequestPurchaseHistory;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.service.UserServiceImplement;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryServiceImpl implements IPurchaseHistoryService{

    private final IPurchaseHistoryRepository iPurchaseHistoryRepository;
    private final IUserRepository iUserRepository;

    @Override
    public PurchaseHistory addPurchaseHistory(RequestPurchaseHistory requestPurchaseHistory) {
        PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                .sum(requestPurchaseHistory.getSum())
                .product(requestPurchaseHistory.getProduct())
                .type(requestPurchaseHistory.getType())
                .amount(requestPurchaseHistory.getAmount())
                .userId(iUserRepository.getReferenceById(requestPurchaseHistory.getUserId()))
                .or_status(requestPurchaseHistory.getOr_status())
                .payment_num(requestPurchaseHistory.getPayment_num())
                .sp_status(requestPurchaseHistory.getSp_status())
                .category(requestPurchaseHistory.getCategory())
                .build();

        return iPurchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public PurchaseHistory getPurchaseHistory(Long id) {
        return iPurchaseHistoryRepository.findById(id).get();
    }
}
