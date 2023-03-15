package sphabucks.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.UserLikes;
import sphabucks.users.repository.IUserLikesRepo;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.vo.RequestUserLikes;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLikesServiceImpl implements IUserLikesService{

    private final IUserLikesRepo iUserLikesRepo;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;
    @Override
    @Transactional
    public void pushUserLikes(RequestUserLikes requestUserLikes) {
        // User가 like를 했을 때
        if(!iUserLikesRepo.existsAllByProductIdAndUserUserId(requestUserLikes.getProductId(), requestUserLikes.getUserId())) {
            iUserLikesRepo.save(UserLikes.builder()
                    .product(iProductRepository.findById(requestUserLikes.getProductId()).get())
                    .user(iUserRepository.findByUserId(requestUserLikes.getUserId()))
                    .build());
            Long count = iProductRepository.findById(requestUserLikes.getProductId()).get().getLikeCount();
            iProductRepository.updateLikeCount(count+1, requestUserLikes.getProductId());
        }else{
            // User가 이미 like를 한 상태일 때
            Long count = iProductRepository.findById(requestUserLikes.getProductId()).get().getLikeCount();
            iProductRepository.updateLikeCount(count-1, requestUserLikes.getProductId());
            iUserLikesRepo.deleteByUserUserId(requestUserLikes.getUserId());
        }
    }

    @Override
    public List<UserLikes> getUserLikes(String userId) {
        return iUserLikesRepo.findUserLikesByUserUserId(userId);
    }

    @Override
    public List<UserLikes> getAll() {
        return iUserLikesRepo.findAll();
    }
}
