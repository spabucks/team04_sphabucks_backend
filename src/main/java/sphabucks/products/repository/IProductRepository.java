package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.Product;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(Long id);
}
