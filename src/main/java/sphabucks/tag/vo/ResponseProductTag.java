package sphabucks.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseProductTag {

    Long productId;
    String name;            // 상품 이름
    Integer price;
    String productImage;    // 상품 사진
    Long productTagId;
    Long tagId;
    String tagImage;        // 기획전 메인 사진

}
