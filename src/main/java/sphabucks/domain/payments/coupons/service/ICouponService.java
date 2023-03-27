package sphabucks.domain.payments.coupons.service;

import sphabucks.domain.payments.coupons.model.Coupon;
import sphabucks.domain.payments.coupons.vo.RequestCoupon;
import sphabucks.domain.payments.coupons.vo.ResponseCoupon;

import java.util.List;

public interface ICouponService {
    void addCoupon(RequestCoupon requestCoupon);

    ResponseCoupon getCoupon(Long id);

    List<Coupon> getAllCoupon();
}
