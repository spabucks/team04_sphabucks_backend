package sphabucks.purchaseHistory.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
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

        // paymentNum 이 현재 중복될 수 도 있는 값이라 paymentNum 생성할 때 수정 필요
        if(iPurchaseHistoryRepository.findByPayment_num(requestPurchaseHistory.getPayment_num()).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_HISTORY, ErrorCode.DUPLICATE_HISTORY.getCode());
        }

        PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                .user(iUserRepository.findById(requestPurchaseHistory.getUserId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                .image(requestPurchaseHistory.getImage())
                .amount(requestPurchaseHistory.getAmount())
                .category(requestPurchaseHistory.getCategory())
                .sum(requestPurchaseHistory.getSum())
                .type(requestPurchaseHistory.getType())
                .payment_num(requestPurchaseHistory.getPayment_num())
                .type(requestPurchaseHistory.getType())
                .sp_status(requestPurchaseHistory.getSp_status())
                .or_status(requestPurchaseHistory.getOr_status())
                .productName(requestPurchaseHistory.getProductName())
                .build();
        return iPurchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public PurchaseHistory getPurchaseHistory(Long id) {
        PurchaseHistory purchaseHistory = iPurchaseHistoryRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.HISTORY_NOT_EXISTS, ErrorCode.HISTORY_NOT_EXISTS.getCode()));
        return purchaseHistory;
    }
}
