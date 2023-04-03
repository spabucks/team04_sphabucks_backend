package sphabucks.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.products.model.ProductCategoryList;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {
    List<ProductCategoryList> findAllByProductId(Long productId);

    List<ProductCategoryList> findTop30ByBigCategoryId(Long bigCategoryId);

    List<ProductCategoryList> findAllBySmallCategoryId(Long sCategoryId);

    ProductCategoryList findByProductId(Long productId);
}
