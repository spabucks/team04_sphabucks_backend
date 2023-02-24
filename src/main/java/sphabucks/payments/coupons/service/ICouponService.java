package sphabucks.payments.coupons.service;

import sphabucks.payments.coupons.model.Coupon;
import sphabucks.payments.coupons.vo.RequestCoupon;
import sphabucks.payments.coupons.vo.ResponseCoupon;

import java.util.List;

public interface ICouponService {
    Coupon addCoupon(RequestCoupon requestCoupon);

    ResponseCoupon getCoupon(Long id);

    List<Coupon> getAllCoupon();
}
