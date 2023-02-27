package sphabucks.payments.coupons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.coupons.model.Coupon;
import sphabucks.payments.coupons.model.CouponList;
import sphabucks.payments.coupons.service.ICouponListService;
import sphabucks.payments.coupons.service.ICouponService;
import sphabucks.payments.coupons.vo.RequestCoupon;
import sphabucks.payments.coupons.vo.RequestCouponList;
import sphabucks.payments.coupons.vo.ResponseCoupon;

import java.util.List;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final ICouponService iCouponService;
    private final ICouponListService iCouponListService;

    @PostMapping("/add")    // DB에 쿠폰 추가
    public void addCoupon(@RequestBody RequestCoupon requestCoupon) {
        iCouponService.addCoupon(requestCoupon);
    }

    @GetMapping("/get/{id}")    // id(pk)로 쿠폰 정보 확인
    public ResponseCoupon getCoupon(@PathVariable Long id) {
        return iCouponService.getCoupon(id);
    }

    @GetMapping("/get/all") // 모든 쿠폰 정보 확인
    public List<Coupon> getAllCoupon() {
        return iCouponService.getAllCoupon();
    }
}
