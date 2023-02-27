package sphabucks.payments.cards.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.payments.cards.model.CardList;
import sphabucks.payments.cards.repository.ICardListRepo;
import sphabucks.payments.cards.repository.ICardRepo;
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
        ModelMapper modelMapper = new ModelMapper();
        CardList cardList = modelMapper.map(requestCardList, CardList.class);
        cardList.setRepresent(false);
        cardList.setStartDate(new Date());
        iCardListRepo.save(cardList);
    }

    @Override
    public List<CardList> getCardList(Long userId) {
        return iCardListRepo.findAllByUserUserId(iUserRepository.findById(userId).get().getUserId());
    }
}
