package sphabucks.payments.cards.service;

import sphabucks.payments.cards.model.Card;
import sphabucks.payments.cards.vo.ResponseCard;

public interface ICardService {
    void addCard(Card card);
    ResponseCard getCard(Long id);
}
