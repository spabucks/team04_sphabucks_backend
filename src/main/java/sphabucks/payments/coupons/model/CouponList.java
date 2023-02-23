package sphabucks.payments.coupons.model;

import jakarta.persistence.*;
import lombok.*;
import sphabucks.users.model.User;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Coupon coupon;
}
