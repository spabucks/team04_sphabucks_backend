package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.Product;
import sphabucks.products.vo.ResponseProduct;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(Long id);

    List<Product> findByNameContains(String keyword);
}
