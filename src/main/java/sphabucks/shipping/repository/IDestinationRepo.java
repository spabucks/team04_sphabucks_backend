package sphabucks.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.shipping.model.Destination;

public interface IDestinationRepo extends JpaRepository<Destination, Long> {
}
