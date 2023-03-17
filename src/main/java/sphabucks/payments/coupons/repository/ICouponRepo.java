package sphabucks.payments.coupons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.coupons.model.Coupon;

import java.util.Optional;

public interface ICouponRepo extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByNumber(String number);
}
