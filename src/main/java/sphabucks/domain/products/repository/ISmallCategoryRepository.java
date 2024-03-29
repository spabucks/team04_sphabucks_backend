package sphabucks.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.products.model.BigCategory;
import sphabucks.domain.products.model.SmallCategory;

import java.util.List;
import java.util.Optional;

public interface ISmallCategoryRepository extends JpaRepository<SmallCategory, Long> {

    List<SmallCategory> findById(BigCategory bigCategory);
    Optional<SmallCategory> findByName(String name);
    List<SmallCategory> findAllByBigCategoryId(Long bigCategoryId);



}
