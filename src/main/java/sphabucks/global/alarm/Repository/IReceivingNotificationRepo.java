package sphabucks.global.alarm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.global.alarm.model.ReceivingNotification;

import java.util.List;
import java.util.Optional;

public interface IReceivingNotificationRepo extends JpaRepository<ReceivingNotification, Long> {
    Optional<ReceivingNotification> findByUser_UserIdAndProductId(String userId, Long productId);
    List<ReceivingNotification> findAllByUser_UserId(String userId);

}
