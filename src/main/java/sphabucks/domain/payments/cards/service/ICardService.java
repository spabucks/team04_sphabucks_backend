package sphabucks.domain.payments.cards.service;

import sphabucks.domain.payments.cards.vo.ResponseCard;
import sphabucks.domain.payments.cards.vo.RequestCard;

public interface ICardService {
    void addCard(RequestCard requestCard);
    ResponseCard getCard(Long id);
}
