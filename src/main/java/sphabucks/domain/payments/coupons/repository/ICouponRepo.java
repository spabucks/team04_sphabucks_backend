package sphabucks.domain.payments.coupons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.payments.coupons.model.Coupon;

import java.util.Optional;

public interface ICouponRepo extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByNumber(String number);
}
