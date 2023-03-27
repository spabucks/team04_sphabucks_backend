package sphabucks.domain.payments.coupons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.coupons.model.Coupon;
import sphabucks.domain.payments.coupons.service.ICouponListService;
import sphabucks.domain.payments.coupons.service.ICouponService;
import sphabucks.domain.payments.coupons.vo.RequestCoupon;
import sphabucks.domain.payments.coupons.vo.ResponseCoupon;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CouponController {

    private final ICouponService iCouponService;
    private final ICouponListService iCouponListService;

    @PostMapping("/add")    // DB에 쿠폰 추가
    @Operation(summary = "쿠폰 등록", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addCoupon(@RequestBody RequestCoupon requestCoupon) {
        iCouponService.addCoupon(requestCoupon);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/{id}")    // id(pk)로 쿠폰 정보 확인
    @Operation(summary = "쿠폰 정보 조회", description = "삭제 예정?")
    public ResponseEntity<Object> getCoupon(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iCouponService.getCoupon(id));
    }

    @GetMapping("/get/all") // 모든 쿠폰 정보 확인
    @Operation(summary = "모든 쿠폰 조회", description = "어드민 권한인데 고객이 사용할 가능성도 있음")
    public ResponseEntity<Object> getAllCoupon() {
        return ResponseEntity.status(HttpStatus.OK).body(iCouponService.getAllCoupon());
    }
}
