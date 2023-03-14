package sphabucks.payments.cards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;    // 카드 이름
    @Column(updatable = false, nullable = false)
    private Long defaultMoney;   // 카드 초기금액
    private String number;  // 카드 번호
    private String pin; // 핀번호(8자리)
    private String image;   // 카드 이미지 경로
    private Long money;  // 현재 잔액
}
