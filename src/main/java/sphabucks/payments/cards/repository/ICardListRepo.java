package sphabucks.payments.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.cards.model.CardList;

import java.util.List;
import java.util.Optional;

public interface ICardListRepo extends JpaRepository<CardList, Long> {
    List<CardList> findAllByUserUserId(String uuid);

}
