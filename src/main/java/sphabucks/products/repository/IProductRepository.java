package sphabucks.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.Product;
import sphabucks.products.vo.ResponseProduct;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(Long id);

    Page<Product> findByNameContains(String keyword, Pageable pageable);
}
