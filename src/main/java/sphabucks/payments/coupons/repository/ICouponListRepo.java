package sphabucks.payments.coupons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.coupons.model.Coupon;
import sphabucks.payments.coupons.model.CouponList;

import java.util.List;

public interface ICouponListRepo extends JpaRepository<CouponList, Long> {
    List<CouponList> findAllByUserUserId(String uuid);
}
