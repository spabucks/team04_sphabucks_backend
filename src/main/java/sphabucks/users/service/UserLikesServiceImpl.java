package sphabucks.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.UserLikes;
import sphabucks.users.repository.IUserLikesRepo;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.vo.RequestUserLikes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLikesServiceImpl implements IUserLikesService{

    private final IUserLikesRepo iUserLikesRepo;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    @Override
    public void addUserLikes(RequestUserLikes requestUserLikes) {
        iUserLikesRepo.save(UserLikes.builder()
                        .product(iProductRepository.findById(requestUserLikes.getProductId()).get())
                        .user(iUserRepository.findById(requestUserLikes.getUserId()).get())
                .build());
    }

    @Override
    public List<UserLikes> getUserLikes(Long userId) {
        return iUserLikesRepo.findUserLikesByUserId(userId);
    }

    @Override
    public List<UserLikes> getAll() {
        return iUserLikesRepo.findAll();
    }
}
