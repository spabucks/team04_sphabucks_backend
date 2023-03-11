package sphabucks.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.tag.model.ProductTag;
import sphabucks.tag.vo.ResponseExhibitionProduct;
import sphabucks.tag.vo.ResponseProductTag;

import java.util.List;

// 진행사항, 이슈상황, 팁 공유


public interface IProductTagRepository extends JpaRepository<ProductTag, Long> {
    List<ProductTag> findAllByProductId(Long productId);
    //    List<ResponseExhibitionProduct> findAllByTagId(Long tagId);
    List<ProductTag> findAllByTagId(Long tagId);
    List<ProductTag> findAllById(Long productTagId);


//    List<ProductTag> findByProductIdOrderByTagId(Long tagId);

//    ProductTag findByTagId(Long tagId);


}
