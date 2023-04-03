package sphabucks.domain.payments.gifticons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftIcon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 기프티콘 id

    @Column(nullable = false)
    private String name;    // 쿠폰명
//    @Column(nullable = false)
    private Date startDate;    // 발행일
//    @Column(nullable = false)
    private Date endDate;  // 만료일
//    @Column(nullable = false)
    private String image;   // 기프티콘 이미지
//    @Column(nullable = false)
    private String barcode;   // 바코드
    private String content; // 내용
//    @Column(nullable = false)
    private String number;  // 기프티콘번호
    private String alt; // 이미지 설명
}
