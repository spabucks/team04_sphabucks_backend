package sphabucks.payments.coupons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.coupons.model.Coupon;

public interface ICouponRepo extends JpaRepository<Coupon, Long> {
}
