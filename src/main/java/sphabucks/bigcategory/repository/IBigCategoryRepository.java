package sphabucks.bigcategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.bigcategory.model.BigCategory;

public interface IBigCategoryRepository extends JpaRepository<BigCategory, Long> {
}
