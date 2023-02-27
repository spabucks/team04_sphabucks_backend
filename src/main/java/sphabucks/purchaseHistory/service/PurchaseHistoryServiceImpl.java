package sphabucks.purchaseHistory.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
        ModelMapper modelMapper = new ModelMapper();
        PurchaseHistory purchaseHistory = modelMapper.map(requestPurchaseHistory, PurchaseHistory.class);

        return iPurchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public PurchaseHistory getPurchaseHistory(Long id) {
        return iPurchaseHistoryRepository.findById(id).get();
    }
}
