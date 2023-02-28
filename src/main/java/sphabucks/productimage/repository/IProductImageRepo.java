package sphabucks.productimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sphabucks.productimage.model.ProductImage;

import java.util.List;

public interface IProductImageRepo extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findAllByProductId(Long productId);
}
