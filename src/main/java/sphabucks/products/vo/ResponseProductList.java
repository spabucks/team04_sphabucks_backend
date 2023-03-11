package sphabucks.products.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseProductList {  // 특정 분류에 따라 해당되는 상품들의 묶음 (ex. 특정 이벤트 상품, 특정 카테고리 상품)
    private Long id;
    private String name;    // 분류 기준 (ex. 이벤트 이름, 카테고리(대, 소) 이름)
    private List<ResponseProductSummary> data; // (관련된 상품 리스트(디테일 정보는 제외한 정보))
}
