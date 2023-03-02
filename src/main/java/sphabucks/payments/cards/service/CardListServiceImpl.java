package sphabucks.payments.cards.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.payments.cards.model.CardList;
import sphabucks.payments.cards.repository.ICardListRepo;
import sphabucks.payments.cards.repository.ICardRepo;
import sphabucks.payments.cards.vo.RequestCard;
import sphabucks.payments.cards.vo.RequestCardList;
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
                .card(iCardRepo.findById(requestCardList.getCardId()).get())
                .user(iUserRepository.findById(requestCardList.getUserId()).get())
                .build();

        iCardListRepo.save(cardList);
    }

    @Override
    public List<CardList> getCardList(Long userId) {
        return iCardListRepo.findAllByUserUserId(iUserRepository.findById(userId).get().getUserId());
    }
}
