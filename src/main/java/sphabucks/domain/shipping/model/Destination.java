package sphabucks.domain.shipping.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import sphabucks.domain.users.model.User;
import sphabucks.global.utility.BaseTimeEntity;

@Entity
@Getter
@Setter
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Destination extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;    // 주소 별칭
    @Column(nullable = false)
    private String recipient;   // 받는 분
    @Column(nullable = false)
    private String defaultAddress;  // 기본 주소
    @Column(nullable = false)
    private String phoneNum;    // 연락처 1
    private String content;     // 배송 메모

    private boolean defaultDestination; // 기본(대표) 배송지

    @ManyToOne
    private User user;
}
