package sphabucks.domain.payments.coupons.service;

import sphabucks.domain.payments.coupons.model.CouponList;
import sphabucks.domain.payments.coupons.vo.RequestCouponList;

import java.util.List;

public interface ICouponListService {
    void addCoupon2User(String userId, RequestCouponList requestCouponList);
    List<CouponList> getCoupon2User(String userId);
}
