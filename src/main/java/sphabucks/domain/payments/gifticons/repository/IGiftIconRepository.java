package sphabucks.domain.payments.gifticons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.payments.gifticons.model.GiftIcon;

import java.util.Optional;

public interface IGiftIconRepository extends JpaRepository<GiftIcon, Long> {
    Optional<GiftIcon> findByNumber(String number);
}
