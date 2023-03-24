package sphabucks.payments.gifticons.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.payments.gifticons.model.GiftIconList;
import sphabucks.payments.gifticons.repository.IGiftIconListRepo;
import sphabucks.payments.gifticons.repository.IGiftIconRepository;
import sphabucks.payments.gifticons.vo.RequestGiftIconList;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftIconListServiceImpl implements IGiftIconListService{

    private final IGiftIconListRepo iGiftIconListRepo;
    private final IGiftIconRepository iGiftIconRepository;
    private final IUserRepository iUserRepository;
    @Override
    public void addGiftIconList(RequestGiftIconList requestGiftIconList) {

        GiftIconList giftIconList = GiftIconList.builder()
                .giftIcon(iGiftIconRepository.findById(requestGiftIconList.getGiftIconId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.GIFTICON_NOT_EXISTS,ErrorCode.GIFTICON_NOT_EXISTS.getCode())))
                .user(iUserRepository.findById(requestGiftIconList.getUserId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                .build();

        iGiftIconListRepo.save(giftIconList);
    }

    @Override
    public List<GiftIconList> getGiftIconList(Long id) {

        User user = iUserRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        if(iGiftIconListRepo.findAllByUserUserId(user.getUserId()).isEmpty()){
            throw new BusinessException(ErrorCode.GIFTICONS_NOT_EXISTS, ErrorCode.GIFTICONS_NOT_EXISTS.getCode());
        }

        return iGiftIconListRepo.findAllByUserUserId(user.getUserId());
    }
}
