package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.BigCategory;

public interface IBigCategoryRepository extends JpaRepository<BigCategory, Integer> {

}
