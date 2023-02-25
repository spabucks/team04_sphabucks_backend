package sphabucks.payments.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.cards.model.Card;

public interface ICardRepo extends JpaRepository<Card, Long> {
}
