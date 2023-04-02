package sphabucks.domain.shipping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseDestinationSummary {

    private Long shippingId;
    private String name;    // 주소 별칭
    private String recipient;   // 받는 분
    private String defaultAddress;  // 기본 주소
    private String phoneNum;    // 연락처 1
    private String content;     // 배송 메모

}
