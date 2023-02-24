package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.Category;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByType(String categoryType);

}
