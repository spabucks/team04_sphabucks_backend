package sphabucks.payments.coupons.service;

import sphabucks.payments.coupons.model.CouponList;
import sphabucks.payments.coupons.vo.RequestCouponList;

import java.util.List;

public interface ICouponListService {
    void addCoupon2User(RequestCouponList requestCouponList);
    List<CouponList> getCoupon2User(Long id);
}
