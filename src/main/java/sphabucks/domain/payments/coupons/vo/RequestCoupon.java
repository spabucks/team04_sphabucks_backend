package sphabucks.domain.payments.coupons.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestCoupon {
    private String name;    // 쿠폰명
    private Date startDate;    // 발행일
    private Date endDate;  // 만료일
    private String image;   // 쿠폰 이미지
    private String qrImage;    // QR 코드 이미지
    private String content; // 쿠폰 내용
    private String number;  // 쿠폰 번호
    private String alt; // 이미지 설명

}
