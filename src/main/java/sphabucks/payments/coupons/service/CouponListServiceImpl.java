package sphabucks.payments.coupons.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.payments.coupons.model.CouponList;
import sphabucks.payments.coupons.repository.ICouponListRepo;
import sphabucks.payments.coupons.repository.ICouponRepo;
import sphabucks.payments.coupons.vo.RequestCouponList;
import sphabucks.users.repository.IUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponListServiceImpl implements ICouponListService{
    private final ICouponListRepo iCouponListRepo;
    private final ICouponRepo iCouponRepo;
    private final IUserRepository iUserRepository;

    @Override
    public CouponList addCoupon2User(RequestCouponList requestCouponList) {
        return iCouponListRepo.save(CouponList.builder()
                        .user(iUserRepository.findById(requestCouponList.getUserId()).get())
                        .coupon(iCouponRepo.findById(requestCouponList.getCouponId()).get())
                        .build());
    }

    @Override
    public List<CouponList> getCoupon2User(Long id) {
        return iCouponListRepo.findAllByUserUserId(iUserRepository.findById(id).get().getUserId());
    }
}
