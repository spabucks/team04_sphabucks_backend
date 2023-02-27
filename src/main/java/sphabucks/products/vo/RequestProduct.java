package sphabucks.products.vo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

import java.util.Date;

@Getter
public class RequestProduct {

    @Column(nullable = false)
    private Integer price;  // 가격
    @Column(nullable = false)
    private Long amount;  // 잔여 개수
    @Column(nullable = false)
    private Date date;  // 출시일
    @Column(nullable = false)
    private String name;    // 상품 이름
    @Column(nullable = false)
    private String description; // 설명
    @Column(nullable = false)
    private String status;    // 상태(입고 구분)
    private String size;  // 용량
    private Boolean isBest;   // 베스트
    private Boolean isNew;
}
