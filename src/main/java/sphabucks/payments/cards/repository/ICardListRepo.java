package sphabucks.payments.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.cards.model.CardList;

import java.util.List;

public interface ICardListRepo extends JpaRepository<CardList, Long> {
    List<CardList> findAllByUserUserId(String uuid);
}
