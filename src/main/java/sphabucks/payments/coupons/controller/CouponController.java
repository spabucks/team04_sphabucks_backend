package sphabucks.payments.coupons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.coupons.model.Coupon;
import sphabucks.payments.coupons.service.ICouponService;
import sphabucks.payments.coupons.vo.RequestCoupon;
import sphabucks.payments.coupons.vo.ResponseCoupon;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("/add")
    public Coupon addCoupon(@RequestBody RequestCoupon requestCoupon) {
        return iCouponService.addCoupon(requestCoupon);
    }

    @GetMapping("/get/{id}")
    public ResponseCoupon getCoupon(@PathVariable Long id) {
        return iCouponService.getCoupon(id);
    }
}
