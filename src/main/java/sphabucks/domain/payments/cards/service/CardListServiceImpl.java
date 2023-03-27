package sphabucks.domain.payments.cards.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.domain.payments.cards.model.CardList;
import sphabucks.domain.payments.cards.repository.ICardRepo;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.payments.cards.repository.ICardListRepo;
import sphabucks.domain.payments.cards.vo.RequestCardList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardListServiceImpl implements ICardListService{
    private final IUserRepository iUserRepository;
    private final ICardRepo iCardRepo;
    private final ICardListRepo iCardListRepo;

    @Override
    public void addCardList(RequestCardList requestCardList) {

        CardList cardList = CardList.builder()
                .card(iCardRepo.findById(requestCardList.getCardId())
                        .orElseThrow(()->new BusinessException(ErrorCode.CARD_NOT_EXISTS, ErrorCode.CARD_NOT_EXISTS.getCode())))
                .user(iUserRepository.findById(requestCardList.getUserId())
                        .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                .build();

        iCardListRepo.save(cardList);
    }

    @Override
    public List<CardList> getCardList(Long userId) {

        User findUser = iUserRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        if(iCardListRepo.findAllByUserUserId(findUser.getUserId()).isEmpty()){
            throw new BusinessException(ErrorCode.CARD_NOT_EXISTS, ErrorCode.CARD_NOT_EXISTS.getCode());
        }

        return iCardListRepo.findAllByUserUserId(findUser.getUserId());
    }
}
