package sphabucks.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.UserWishlist;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.repository.IUserWishlistRepo;
import sphabucks.users.vo.RequestUserWishlist;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWishlistServiceImpl implements IUserWishlistService {

    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IUserWishlistRepo iUserWishlistRepo;

    @Override
    public void addUserWishlist(RequestUserWishlist requestUserWishlist) {
        iUserWishlistRepo.save(UserWishlist.builder()
                        .product(iProductRepository.findById(requestUserWishlist.getProductId()).get())
                        .user(iUserRepository.findById(requestUserWishlist.getUserId()).get())
                .build());
    }

    @Override
    public List<UserWishlist> getByUserWishlist(Long userId) {
        return iUserWishlistRepo.findAllByUserId(userId);
    }
}
