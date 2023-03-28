package sphabucks.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.products.model.BigCategory;

import java.util.Optional;

public interface IBigCategoryRepository extends JpaRepository<BigCategory, Long> {
    Optional<BigCategory> findByName(String name);
}
