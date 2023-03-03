package sphabucks.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseProductTag {

    Long productTagId;
    Long tagId;
    String tagImage;        // 기획전 메인 사진
    List<ResponseExhibitionProduct> responseExhibitionProduct;


}
