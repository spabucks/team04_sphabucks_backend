package sphabucks.products.model;

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
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 상품 id

    private Integer price;  // 가격
    private Long amount;  // 잔여 개수
    private Date date;  // 출시일
    private String name;    // 상품 이름
    private String description; // 설명
    private String status;    // 상태(입고 구분)
    private String size;  // 용량
    private Boolean isBest;   // 베스트
    private Boolean isNew;
}
