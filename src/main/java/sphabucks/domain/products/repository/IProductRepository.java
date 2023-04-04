package sphabucks.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sphabucks.domain.products.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContains(String keyword);

    Optional<Product> findByName(String name);



    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET p.likeCount = :likeCount where p.id = :id ")
    void updateLikeCount(@Param(value="likeCount") Long likeCount, @Param(value="id") Long id);


}
