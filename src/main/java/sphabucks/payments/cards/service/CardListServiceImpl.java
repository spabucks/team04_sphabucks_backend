package sphabucks.payments.cards.service;

import lombok.RequiredArgsConstructor;
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
        iCardListRepo.save(CardList.builder()
                        .user(iUserRepository.findById(requestCardList.getUserId()).get())
                        .card(iCardRepo.findById(requestCardList.getCardId()).get())
                        .isRepresent(false) // 대표카드 설정 기본값 = false
                        .startDate(new Date())  // 카드를 등록하는 순간 현재 Date 저장
                        .build());
    }

    @Override
    public List<CardList> getCardList(Long userId) {
        return iCardListRepo.findAllByUserUserId(iUserRepository.findById(userId).get().getUserId());
    }
}
