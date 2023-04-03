package sphabucks.domain.payments.coupons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.coupons.service.ICouponListService;
import sphabucks.domain.payments.coupons.vo.RequestCouponList;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/coupon-list")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CouponListController {
    private final ICouponListService iCouponListService;

    @PostMapping("/add/user")
    @Operation(summary = "고객이 자신의 계정이 쿠폰 등록", description = "구현 X")
    public ResponseEntity<Object> addCoupon2User(
            Authentication authentication,
            @RequestBody RequestCouponList requestCouponList) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iCouponListService.addCoupon2User(userId, requestCouponList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/user")
    @Operation(summary = "고객이 자신이 가지고 있는 모든 쿠폰 확인", description = "구현 X")
    public ResponseEntity<Object> getCoupon2User(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, iCouponListService.getCoupon2User(userId)));
    }
}
