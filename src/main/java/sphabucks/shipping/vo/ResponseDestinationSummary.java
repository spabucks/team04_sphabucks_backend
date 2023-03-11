package sphabucks.shipping.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseDestinationSummary {

    private String name;    // 주소 별칭
    private String recipient;   // 받는 분
    private String zipCode;     //우편 번호
    private String defaultAddress;  // 기본 주소
    private String detailAddress;   // 상세 주소
    private String phoneNum;    // 연락처 1
    private String phoneNum2;   // 연락처 2
    private String content;     // 배송 메모

}
