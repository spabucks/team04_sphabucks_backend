package sphabucks.domain.tag.vo;

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
    Long id;
    String tagName;
    String imgUrl;        // 기획전 메인 사진
    List<ResponseExhibitionProduct> data;


}
