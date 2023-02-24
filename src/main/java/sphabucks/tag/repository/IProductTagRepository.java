package sphabucks.product_tag_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.product_tag_list.model.ProductTag;


public interface IProductTagRepository extends JpaRepository<ProductTag, Long> {
}
