package sphabucks.productimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sphabucks.productimage.model.ProductImage;

import java.util.List;

public interface IProductImageRepo extends JpaRepository<ProductImage, Long> {
    @Query(value = "select * from product_image where product_id = ? order by chk",nativeQuery = true)
    List<ProductImage> findAllByProductId(Long productId);
    ProductImage findByProductIdAndChkIsTrue(Long productId);  // productId에 해당하는 제품의 대표사진(썸네일)을 찾기

    ProductImage findFirstByProductId(Long productId);
}
