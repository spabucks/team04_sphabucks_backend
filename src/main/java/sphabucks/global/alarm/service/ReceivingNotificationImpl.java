package sphabucks.global.alarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.global.alarm.Repository.IReceivingNotificationRepo;
import sphabucks.global.alarm.model.ReceivingNotification;
import sphabucks.global.alarm.vo.RequestReceivingNotification;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.users.repository.IUserRepository;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceivingNotificationImpl implements IReceivingNotificationService {

    private final IReceivingNotificationRepo iReceivingNotificationRepo;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;


    @Override
    public void addReceivingNotification(String userId, RequestReceivingNotification requestReceivingNotification) {

        if(iReceivingNotificationRepo.findByUser_UserIdAndProductId(userId, requestReceivingNotification.getProductId()).isPresent()) {
            if (iReceivingNotificationRepo.findByUser_UserIdAndProductId(userId, requestReceivingNotification.getProductId()).get().getIsState() == 1)
                throw new BusinessException(ErrorCode.DUPLICATE_NOTIFICATION, ErrorCode.DUPLICATE_NOTIFICATION.getCode());
        }

        StringBuilder sb = new StringBuilder();
        ReceivingNotification receivingNotification = ReceivingNotification.builder()
                .expirationDate(new Date(System.currentTimeMillis() + (long) 1000 * 60 * 60 * 24 * 14))
                .isState(1L)
                .notificationDateMemo(String.valueOf(sb.append(new Date(System.currentTimeMillis())).append(" ").append(new Date(System.currentTimeMillis() + (long) 1000 * 60 * 60 * 24 * 14))))
                .user(iUserRepository.findByUserId(userId)
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                .product(iProductRepository.findById(requestReceivingNotification.getProductId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode())))
                .build();

        iReceivingNotificationRepo.save(receivingNotification);
    }

    @Override
    public List<ReceivingNotification> getReceivingNotification(String userId) {

        if(iReceivingNotificationRepo.findAllByUser_UserId(userId).isEmpty()){
            throw new BusinessException(ErrorCode.NOTIFICATION_NOT_EXISTS, ErrorCode.NOTIFICATION_NOT_EXISTS.getCode());
        }
        List<ReceivingNotification> receivingNotificationList = iReceivingNotificationRepo.findAllByUser_UserId(userId);
        for (ReceivingNotification receivingNotification : receivingNotificationList) {
            if (new Date(System.currentTimeMillis()).after(receivingNotification.getExpirationDate())) {
                // 현재시간이 알림 만료일 이후 일 때
                StringBuilder sb = new StringBuilder();
                receivingNotification.setNotificationDateMemo(String.valueOf(sb.append(receivingNotification.getExpirationDate()).append(" 해제")));
                receivingNotification.setIsState(2L);
            }
        }

        return iReceivingNotificationRepo.findAllByUser_UserId(userId);
    }
}
