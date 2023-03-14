package sphabucks.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.User;
import sphabucks.users.model.UserWishlist;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.repository.IUserWishlistRepo;
import sphabucks.users.vo.RequestUserWishlist;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWishlistServiceImpl implements IUserWishlistService {
    private final IUserWishlistRepo iUserWishlistRepo;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;


    @Override
    @Transactional
    public void clickWishList(RequestUserWishlist requestUserWishlist) {

        // uuid 를 이용하여 조회한 user
        User user = iUserRepository.findByUserId(requestUserWishlist.getUserId());

        // 해당 유저가 상품을 위시리스트에 추가했던 내역이 있었다면
        if (iUserWishlistRepo.existsByUserUserIdAndProductId(requestUserWishlist.getUserId(), user.getId())) {
            UserWishlist wishlist = iUserWishlistRepo.findByUserId(user.getId());
            wishlist.setIsDeleted(!wishlist.getIsDeleted());
        } else { // 위시 리스트에 추가 되었던 내역이 없다면
            iUserWishlistRepo.save(UserWishlist.builder()
                            .user(user)
                            .product(iProductRepository.findById(requestUserWishlist.getProductId()).get())
                            .isDeleted(false)
                    .build());
        }
    }

    @Override
    public List<UserWishlist> getByUserWishlist(Long userId) {
        return iUserWishlistRepo.findAllByUserId(userId);
    }
}
