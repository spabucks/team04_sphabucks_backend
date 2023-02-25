package sphabucks.payments.cards.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String name;    // 카드 이름
    private Integer defaultMoney;   // 카드 초기금액
    private String number;  // 카드 번호
    private String pin; // 핀번호(8자리)
    private String image;   // 카드 이미지 경로
    private Integer money;  // 현재 잔액
}
