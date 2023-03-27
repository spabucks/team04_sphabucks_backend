package sphabucks.domain.payments.coupons.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.domain.payments.coupons.model.CouponList;
import sphabucks.domain.payments.coupons.repository.ICouponListRepo;
import sphabucks.domain.payments.coupons.repository.ICouponRepo;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.payments.coupons.vo.RequestCouponList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponListServiceImpl implements ICouponListService{
    private final ICouponListRepo iCouponListRepo;
    private final ICouponRepo iCouponRepo;
    private final IUserRepository iUserRepository;

    @Override
    public void addCoupon2User(RequestCouponList requestCouponList) {

        CouponList couponList = CouponList.builder()
                .coupon(iCouponRepo.findById(requestCouponList.getCouponId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.COUPON_NOT_EXISTS,ErrorCode.COUPON_NOT_EXISTS.getCode())))
                .user(iUserRepository.findById(requestCouponList.getUserId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                .build();

        iCouponListRepo.save(couponList);
    }

    @Override
    public List<CouponList> getCoupon2User(Long id) {

        User user = iUserRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        if(iCouponListRepo.findAllByUserUserId(user.getUserId()).isEmpty()){
            throw new BusinessException(ErrorCode.COUPONS_NOT_EXISTS, ErrorCode.COUPONS_NOT_EXISTS.getCode());
        }

        return iCouponListRepo.findAllByUserUserId(user.getUserId());
    }
}
