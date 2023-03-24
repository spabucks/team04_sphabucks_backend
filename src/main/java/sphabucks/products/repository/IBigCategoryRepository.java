package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.BigCategory;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IBigCategoryRepository extends JpaRepository<BigCategory, Long> {
    Optional<BigCategory> findByName(String name);
}
