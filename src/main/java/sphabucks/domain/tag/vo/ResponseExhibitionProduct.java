package sphabucks.domain.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseExhibitionProduct {

//    Long tagId;
    Long id;
    String title;            // 상품 이름
    Long price;
//    ExhibitionProductImage productImage;    // 상품 사진
    String imgUrl;
    Boolean isNew;
    Boolean isBest;
    Long amount;
}
