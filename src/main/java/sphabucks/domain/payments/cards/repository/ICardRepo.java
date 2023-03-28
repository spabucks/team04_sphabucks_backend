package sphabucks.domain.payments.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.payments.cards.model.Card;

import java.util.Optional;

public interface ICardRepo extends JpaRepository<Card, Long> {
    Optional<Card> findByNumber(String number);
}
