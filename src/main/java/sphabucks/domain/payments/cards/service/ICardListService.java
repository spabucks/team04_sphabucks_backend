package sphabucks.domain.payments.cards.service;

import sphabucks.domain.payments.cards.model.CardList;
import sphabucks.domain.payments.cards.vo.RequestCardList;
import sphabucks.global.auth.vo.RequestHead;

import java.util.List;

public interface ICardListService {
    void addCardList(RequestHead requestHead, RequestCardList requestCardList);
    List<CardList> getCardList(Long userId);
}
