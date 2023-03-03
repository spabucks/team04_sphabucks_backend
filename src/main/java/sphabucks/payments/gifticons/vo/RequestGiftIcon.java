package sphabucks.payments.gifticons.vo;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class RequestGiftIcon {
    private String name;
    private String image;   // 기프티콘 이미지
    private String barcode;   // 바코드
    private String content; // 내용
    private String number;  // 기프티콘번호
    private String alt; // 이미지 설명
    private Date startDate;    // 발행일
    private Date endDate;  // 만료일

}
