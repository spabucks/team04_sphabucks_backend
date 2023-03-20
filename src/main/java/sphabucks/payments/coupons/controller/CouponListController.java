package sphabucks.payments.coupons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.coupons.model.CouponList;
import sphabucks.payments.coupons.service.ICouponListService;
import sphabucks.payments.coupons.vo.RequestCouponList;

import java.util.List;

@RestController
@RequestMapping("/api/coupon-list")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CouponListController {
    private final ICouponListService iCouponListService;

    @PostMapping("/v1/add/user")    // 사용자 id와 쿠폰 id를 이용하여 쿠폰 리스트에 정보 추가
    @Operation(summary = "고객이 자신의 계정이 쿠폰 등록", description = "구현 X")
    public void addCoupon2User(@RequestBody RequestCouponList requestCouponList) {
        iCouponListService.addCoupon2User(requestCouponList);
    }

    @GetMapping("/v1/get/user/{id}")   // 사용자별 가지고 있는 모든 쿠폰조회
    @Operation(summary = "고객이 자신이 가지고 있는 모든 쿠폰 확인", description = "구현 X")
    public List<CouponList> getCoupon2User(@PathVariable Long id) {
        return iCouponListService.getCoupon2User(id);
    }
}
