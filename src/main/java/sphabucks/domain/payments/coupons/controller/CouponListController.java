package sphabucks.domain.payments.coupons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.coupons.model.CouponList;
import sphabucks.domain.payments.coupons.service.ICouponListService;
import sphabucks.domain.payments.coupons.vo.RequestCouponList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon-list")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CouponListController {
    private final ICouponListService iCouponListService;

    @PostMapping("/add/user")    // 사용자id과 쿠폰id를 이용하여 쿠폰 리스트에 정보 추가
    @Operation(summary = "고객이 자신의 계정이 쿠폰 등록", description = "구현 X")
    public ResponseEntity<Object> addCoupon2User(@RequestBody RequestCouponList requestCouponList) {
        iCouponListService.addCoupon2User(requestCouponList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/user/{id}")   // 사용자별 가지고 있는 모든 쿠폰조회
    @Operation(summary = "고객이 자신이 가지고 있는 모든 쿠폰 확인", description = "구현 X")
    public ResponseEntity<Object> getCoupon2User(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iCouponListService.getCoupon2User(id));
    }
}
