package sphabucks.products.model;

import jakarta.persistence.*;
import lombok.*;
import sphabucks.utility.BaseTimeEntity;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 상품 id
    @Column(nullable = false)
    private Long price;  // 가격
    @Column(nullable = false)
    private Long amount;  // 잔여 개수
    @Column(nullable = false)
    private String name;    // 상품 이름
    @Column(nullable = false)
    private String description; // 설명
    @Column(nullable = false)
    private String status;    // 상태(입고 구분)
    private String size;  // 용량
    private Boolean isBest;   // 베스트
    private Boolean isNew;
    private Long likeCount;
}
