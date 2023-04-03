package sphabucks.domain.payments.gifticons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.payments.gifticons.model.GiftIconList;

import java.util.List;

public interface IGiftIconListRepo extends JpaRepository<GiftIconList, Long> {
    List<GiftIconList> findAllByUserUserId(String uuid);
}
