package sphabucks.payments.coupons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.coupons.model.CouponList;
import sphabucks.payments.coupons.service.ICouponListService;
import sphabucks.payments.coupons.vo.RequestCouponList;

import java.util.List;

@RestController
@RequestMapping("/coupon-list")
@RequiredArgsConstructor
public class CouponListController {
    private final ICouponListService iCouponListService;

    @PostMapping("/add/user")    // 사용자id과 쿠폰id를 이용하여 쿠폰 리스트에 정보 추가
    public void addCoupon2User(@RequestBody RequestCouponList requestCouponList) {
        iCouponListService.addCoupon2User(requestCouponList);
    }

    @GetMapping("/get/user/{id}")   // 사용자별 가지고 있는 모든 쿠폰조회
    public List<CouponList> getCoupon2User(@PathVariable Long id) {
        return iCouponListService.getCoupon2User(id);
    }
}
