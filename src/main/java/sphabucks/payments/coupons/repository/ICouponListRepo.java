package sphabucks.payments.coupons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.payments.coupons.model.CouponList;

public interface ICouponListRepo extends JpaRepository<CouponList, Long> {
}
