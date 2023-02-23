package sphabucks.payments.coupons.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.payments.coupons.model.CouponList;
import sphabucks.payments.coupons.repository.ICouponListRepo;
import sphabucks.payments.coupons.repository.ICouponRepo;
import sphabucks.payments.coupons.vo.RequestCouponList;
import sphabucks.users.repository.IUserRepository;

@Service
@RequiredArgsConstructor
public class CouponListServiceImpl implements ICouponListService{
    private final ICouponListRepo iCouponListRepo;
    private final ICouponRepo iCouponRepo;
    private final IUserRepository iUserRepository;

    @Override
    public CouponList addCoupon2User(RequestCouponList requestCouponList) {
        return null;
    }

    @Override
    public CouponList getCoupon2User(Long id) {
        return null;
    }
}
