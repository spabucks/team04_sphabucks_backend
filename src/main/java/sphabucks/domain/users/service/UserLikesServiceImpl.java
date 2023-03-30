package sphabucks.domain.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.users.model.UserLikes;
import sphabucks.domain.users.repository.IUserLikesRepo;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.domain.users.vo.RequestUserLikes;
import sphabucks.global.auth.vo.RequestHead;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;

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
    public void pushUserLikes(String userId, RequestUserLikes requestUserLikes) {
        // User가 like를 했을 때
        if(!iUserLikesRepo.existsAllByProductIdAndUserUserId(requestUserLikes.getProductId(), userId)) {
            iUserLikesRepo.save(UserLikes.builder()
                    .product(iProductRepository.findById(requestUserLikes.getProductId())
                            .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode())))
                    .user(iUserRepository.findByUserId(userId)
                            .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                    .build());
            Long count = iProductRepository.findById(requestUserLikes.getProductId())
                    .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()))
                    .getLikeCount();
            iProductRepository.updateLikeCount(count+1, requestUserLikes.getProductId());
        }else{
            // User가 이미 like를 한 상태일 때
            Long count = iProductRepository.findById(requestUserLikes.getProductId())
                    .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()))
                    .getLikeCount();
            iProductRepository.updateLikeCount(count-1, requestUserLikes.getProductId());
            iUserLikesRepo.deleteByUserUserId(userId);
        }
    }

    @Override
    public List<UserLikes> getUserLikes(String userId) {

        if(iUserLikesRepo.findUserLikesByUserUserId(userId).isEmpty()){
            throw new BusinessException(ErrorCode.LIKE_NOT_EXISTS, ErrorCode.LIKE_NOT_EXISTS.getCode());
        }

        return iUserLikesRepo.findUserLikesByUserUserId(userId);
    }

    @Override
    public List<UserLikes> getAll() {

        if(iUserLikesRepo.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.LIKE_NOT_EXISTS, ErrorCode.LIKE_NOT_EXISTS.getCode());
        }

        return iUserLikesRepo.findAll();
    }
}
