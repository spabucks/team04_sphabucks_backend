package sphabucks.payments.coupons.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.payments.coupons.model.Coupon;
import sphabucks.payments.coupons.repository.ICouponRepo;
import sphabucks.payments.coupons.vo.RequestCoupon;
import sphabucks.payments.coupons.vo.ResponseCoupon;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements ICouponService{

    private final ICouponRepo iCouponRepo;

    @Override
    public Coupon addCoupon(RequestCoupon requestCoupon) {
        Coupon coupon = Coupon.builder()
                .name(requestCoupon.getName())
                .startDate(requestCoupon.getStartDate())
                .endDate(requestCoupon.getEndDate())
                .image(requestCoupon.getImage())
                .qrImage(requestCoupon.getQrImage())
                .content(requestCoupon.getContent())
                .number(requestCoupon.getNumber())
                .alt(requestCoupon.getAlt())
                .build();

        return iCouponRepo.save(coupon);
    }

    @Override
    public ResponseCoupon getCoupon(Long id) {
        Coupon coupon = iCouponRepo.findById(id).get();

        ResponseCoupon responseCoupon = ResponseCoupon.builder()
                .Id(coupon.getId())
                .name(coupon.getName())
                .endDate(coupon.getEndDate())
                .content(coupon.getContent())
                .build();
        return responseCoupon;
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return iCouponRepo.findAll();
    }
}
