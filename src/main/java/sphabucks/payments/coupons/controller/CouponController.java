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
    public Coupon addCoupon(@RequestBody RequestCoupon requestCoupon) {
        return iCouponService.addCoupon(requestCoupon);
    }

    @GetMapping("/get/{id}")    // id(pk)로 쿠폰 정보 확인
    public ResponseCoupon getCoupon(@PathVariable Long id) {
        return iCouponService.getCoupon(id);
    }

    @GetMapping("/get/all") // 모든 쿠폰 정보 확인
    public List<Coupon> getAllCoupon() {
        return iCouponService.getAllCoupon();
    }

    @PostMapping("/add/user")    // 사용자id과 쿠폰id를 이용하여 쿠폰 리스트에 정보 추가
    public CouponList addCoupon2User(@RequestBody RequestCouponList requestCouponList) {
        return iCouponListService.addCoupon2User(requestCouponList);
    }

    @GetMapping("/get/user/{id}")   // 사용자별 가지고 있는 모든 쿠폰조회
    public List<CouponList> getCoupon2User(@PathVariable Long id) {
        return iCouponListService.getCoupon2User(id);
    }
}
