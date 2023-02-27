package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.BigCategory;
import sphabucks.products.model.SmallCategory;

import java.util.List;

public interface ISmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {

    List<SmallCategory> findById(BigCategory bigCategory);


}
