package sphabucks.payments.coupons.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
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
    public void addCoupon(RequestCoupon requestCoupon) {

        if(iCouponRepo.findByNumber(requestCoupon.getNumber()).isEmpty()){
            throw new BusinessException(ErrorCode.DUPLICATE_COUPON, ErrorCode.DUPLICATE_COUPON.getCode());
        }

        ModelMapper modelMapper = new ModelMapper();
        Coupon coupon = modelMapper.map(requestCoupon, Coupon.class);
        iCouponRepo.save(coupon);
    }

    @Override
    public ResponseCoupon getCoupon(Long id) {

        Coupon coupon = iCouponRepo.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.COUPON_NOT_EXISTS, ErrorCode.COUPON_NOT_EXISTS.getCode()));

        ResponseCoupon responseCoupon = ResponseCoupon.builder()
                .Id(coupon.getId())
                .name(coupon.getName())
                .endDate(coupon.getEndDate())
                .content(coupon.getContent())
                .image(coupon.getImage())
                .build();

        return responseCoupon;
    }

    @Override
    public List<Coupon> getAllCoupon() {

        if(iCouponRepo.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.COUPONS_NOT_EXISTS, ErrorCode.COUPONS_NOT_EXISTS.getCode());
        }

        return iCouponRepo.findAll();
    }
}
