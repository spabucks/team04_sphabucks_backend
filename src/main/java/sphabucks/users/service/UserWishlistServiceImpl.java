package sphabucks.users.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.UserWishlist;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.repository.IUserWishlistRepo;
import sphabucks.users.vo.RequestUserWishlist;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWishlistServiceImpl implements IUserWishlistService {
    private final IUserWishlistRepo iUserWishlistRepo;

    @Override
    public void addUserWishlist(RequestUserWishlist requestUserWishlist) {
        ModelMapper modelMapper = new ModelMapper();
        UserWishlist userWishlist = modelMapper.map(requestUserWishlist, UserWishlist.class);
        iUserWishlistRepo.save(userWishlist);
    }

    @Override
    public List<UserWishlist> getByUserWishlist(Long userId) {
        return iUserWishlistRepo.findAllByUserId(userId);
    }
}
