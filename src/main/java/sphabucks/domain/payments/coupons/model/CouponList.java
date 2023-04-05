package sphabucks.domain.payments.coupons.model;

import jakarta.persistence.*;
import lombok.*;
import sphabucks.domain.users.model.User;
import sphabucks.global.utility.BaseTimeEntity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Coupon coupon;
}
