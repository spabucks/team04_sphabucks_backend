package sphabucks.domain.payments.cards.service;

import sphabucks.domain.payments.cards.model.CardList;
import sphabucks.domain.payments.cards.vo.RequestCardList;

import java.util.List;

public interface ICardListService {
    void addCardList(String userId, RequestCardList requestCardList);
    List<CardList> getCardList(Long userId);
}
