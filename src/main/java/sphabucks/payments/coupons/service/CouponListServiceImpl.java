package sphabucks.payments.coupons.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public void addCoupon2User(RequestCouponList requestCouponList) {
        ModelMapper modelMapper = new ModelMapper();
        CouponList couponList = modelMapper.map(requestCouponList, CouponList.class);
        iCouponListRepo.save(couponList);
    }

    @Override
    public List<CouponList> getCoupon2User(Long id) {
        return iCouponListRepo.findAllByUserUserId(iUserRepository.findById(id).get().getUserId());
    }
}
