package sphabucks.domain.payments.coupons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 쿠폰 id
    private String name;    // 쿠폰명
    private Date startDate;    // 발행일
    private Date endDate;  // 만료일
    private String image;   // 쿠폰 이미지
    private String qrImage;    // QR코드 이미지
    private String content; // 쿠폰 내용
    private String number;  // 쿠폰 번호
    private String alt; // 이미지 설명
}
