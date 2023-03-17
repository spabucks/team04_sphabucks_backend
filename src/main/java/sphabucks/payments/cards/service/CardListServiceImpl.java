package sphabucks.payments.cards.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.payments.cards.model.CardList;
import sphabucks.payments.cards.repository.ICardListRepo;
import sphabucks.payments.cards.repository.ICardRepo;
import sphabucks.payments.cards.vo.RequestCard;
import sphabucks.payments.cards.vo.RequestCardList;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;

import java.util.Date;
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
                .orElseThrow(()-> new BusinessException(ErrorCode.CARDS_NOT_EXISTS, ErrorCode.CARDS_NOT_EXISTS.getCode()));

        if(iCardListRepo.findAllByUserUserId(findUser.getUserId()).isEmpty()){
            throw new BusinessException(ErrorCode.CARD_NOT_EXISTS, ErrorCode.CARD_NOT_EXISTS.getCode());
        }

        return iCardListRepo.findAllByUserUserId(findUser.getUserId());
    }
}
