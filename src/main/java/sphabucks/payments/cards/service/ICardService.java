package sphabucks.payments.cards.service;

import sphabucks.payments.cards.vo.RequestCard;
import sphabucks.payments.cards.vo.ResponseCard;

public interface ICardService {
    void addCard(RequestCard requestCard);
    ResponseCard getCard(Long id);

    void test();
}
