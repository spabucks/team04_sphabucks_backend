package sphabucks.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.products.model.ProductCategoryList;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {
    List<ProductCategoryList> findAllByProductId(Long productId);

    List<ProductCategoryList> findAllBySmallCategoryId(Integer sCategoryId);
}
