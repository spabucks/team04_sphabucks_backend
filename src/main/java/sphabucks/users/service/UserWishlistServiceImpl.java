package sphabucks.users.service;

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
    public void addUserWishlist(RequestUserWishlist requestUserWishlist) {
        iUserWishlistRepo.save(UserWishlist.builder()
                        .user(iUserRepository.findById(requestUserWishlist.getUserId()).get())
                        .product(iProductRepository.findById(requestUserWishlist.getProductId()).get())
                .build());
    }

    @Override
    public List<UserWishlist> getByUserWishlist(Long userId) {
        return iUserWishlistRepo.findAllByUserId(userId);
    }
}
