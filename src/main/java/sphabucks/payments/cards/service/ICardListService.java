package sphabucks.payments.cards.service;

import sphabucks.payments.cards.model.CardList;
import sphabucks.payments.cards.vo.RequestCardList;

import java.util.List;

public interface ICardListService {
    void addCardList(RequestCardList requestCardList);
    List<CardList> getCardList(Long userId);
}
