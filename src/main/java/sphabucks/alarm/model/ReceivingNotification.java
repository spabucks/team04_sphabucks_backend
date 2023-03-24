package sphabucks.alarm.model;


import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.products.model.Product;
import sphabucks.users.model.User;
import sphabucks.utility.BaseTimeEntity;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceivingNotification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long isState; // 1 : 알림 대기 , 2 : 알림 해제 , 3 : 알림 완료
    private Date expirationDate; // 알림 만료일
    private String notificationDateMemo; // 알림 날짜 메모 ( 입고 알림 내역 )

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}

