package sphabucks.payments.coupons.service;

import sphabucks.payments.coupons.model.CouponList;
import sphabucks.payments.coupons.vo.RequestCouponList;

public interface ICouponListService {
    CouponList addCoupon2User(RequestCouponList requestCouponList);
    CouponList getCoupon2User(Long id);
}
