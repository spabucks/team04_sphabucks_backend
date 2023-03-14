package sphabucks.payments.coupons.model;

import jakarta.persistence.*;
import lombok.*;
import sphabucks.users.model.User;
import sphabucks.utility.BaseTimeEntity;

@Entity
@Builder
@Data
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
