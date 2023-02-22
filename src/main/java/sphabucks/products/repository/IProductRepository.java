package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
