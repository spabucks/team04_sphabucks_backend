package sphabucks.domain.productimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sphabucks.domain.productimage.model.ProductImage;

import java.util.List;
import java.util.Optional;

public interface IProductImageRepo extends JpaRepository<ProductImage, Long> {
    @Query(value = "select * from product_image where product_id = ? order by chk",nativeQuery = true)
    List<ProductImage> findAllByProductId(Long productId);

    List<ProductImage> findAllByProductIdAndChk(Long productId, Integer chk);


}
