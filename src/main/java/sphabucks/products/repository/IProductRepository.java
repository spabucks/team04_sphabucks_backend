package sphabucks.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sphabucks.products.model.Product;
import sphabucks.products.vo.ResponseProduct;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(Long id);

    Page<Product> findByNameContains(String keyword, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET p.likeCount = :likeCount where p.id = :id ")
    void updateLikeCount(@Param(value="likeCount") Long likeCount, @Param(value="id") Long id);
}
