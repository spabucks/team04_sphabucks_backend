package sphabucks.payments.gifticons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.gifticons.model.GiftIcon;

public interface IGiftIconRepository extends JpaRepository<GiftIcon, Long> {
}
