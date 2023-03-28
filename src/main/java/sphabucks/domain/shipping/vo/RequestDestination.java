package sphabucks.domain.shipping.vo;

import lombok.Data;

@Data
public class RequestDestination {

    private String name;    // 주소 별칭
    private String recipient;   // 받는 분
    private String zipCode;     //우편 번호
    private String defaultAddress;  // 기본 주소
    private String detailAddress;   // 상세 주소
    private String phoneNum;    // 연락처 1
    private String phoneNum2;   // 연락처 2
    private String content;     // 배송 메모

    private Boolean defaultDestination; // 기본 배송지로 저장합니다. 체크 유무
}