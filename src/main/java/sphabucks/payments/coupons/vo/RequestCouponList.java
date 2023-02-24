package sphabucks.payments.coupons.vo;

import lombok.Data;

@Data
public class RequestCouponList {
    private Long userId;
    private Long couponId;
}
