package sphabucks.domain.payments.gifticons.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.domain.payments.gifticons.model.GiftIconList;
import sphabucks.domain.payments.gifticons.repository.IGiftIconListRepo;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.payments.gifticons.repository.IGiftIconRepository;
import sphabucks.domain.payments.gifticons.vo.RequestGiftIconList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftIconListServiceImpl implements IGiftIconListService{

    private final IGiftIconListRepo iGiftIconListRepo;
    private final IGiftIconRepository iGiftIconRepository;
    private final IUserRepository iUserRepository;
    @Override
    public void addGiftIconList(String userId, RequestGiftIconList requestGiftIconList) {

        GiftIconList giftIconList = GiftIconList.builder()
                .giftIcon(iGiftIconRepository.findById(requestGiftIconList.getGiftIconId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.GIFTICON_NOT_EXISTS,ErrorCode.GIFTICON_NOT_EXISTS.getCode())))
                .user(iUserRepository.findByUserId(userId)
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                .build();

        iGiftIconListRepo.save(giftIconList);
    }

    @Override
    public List<GiftIconList> getGiftIconList(String userId) {

        User user = iUserRepository.findByUserId(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        if (iGiftIconListRepo.findAllByUserUserId(user.getUserId()).isEmpty()){
            throw new BusinessException(ErrorCode.GIFTICONS_NOT_EXISTS, ErrorCode.GIFTICONS_NOT_EXISTS.getCode());
        }

        return iGiftIconListRepo.findAllByUserUserId(user.getUserId());
    }
}
