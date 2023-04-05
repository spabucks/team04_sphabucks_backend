package sphabucks.domain.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.tag.model.ProductTag;

import java.util.List;
import java.util.Optional;

// 진행사항, 이슈상황, 팁 공유


public interface IProductTagRepository extends JpaRepository<ProductTag, Long> {
    List<ProductTag> findAllByProductId(Long productId);
    List<ProductTag> findAllByTagId(Long tagId);
    List<ProductTag> findAllById(Long productTagId);

    Optional<ProductTag> findByProductIdAndTagId(Long productId, Long tagId);

}
