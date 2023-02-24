package sphabucks.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.tag.model.ProductTag;

import java.util.List;

// 진행사항, 이슈상황, 팁 공유


public interface IProductTagRepository extends JpaRepository<ProductTag, Long> {
    List<ProductTag> findAllByProductId(Long productId);
}
