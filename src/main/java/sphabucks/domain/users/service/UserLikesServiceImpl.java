package sphabucks.domain.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.ReactiveZSetCommands;
import org.springframework.stereotype.Service;
import sphabucks.domain.productimage.repository.IProductImageRepo;
import sphabucks.domain.productimage.service.IProductImageService;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.users.model.UserLikes;
import sphabucks.domain.users.repository.IUserLikesRepo;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.domain.users.vo.RequestUserLikes;
import sphabucks.domain.users.vo.ResponseUserLikes;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLikesServiceImpl implements IUserLikesService{

    private final IUserLikesRepo iUserLikesRepo;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;
    private final IProductImageRepo iProductImageRepo;

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
            iUserLikesRepo.deleteByUserUserIdAndProductId(userId, requestUserLikes.getProductId());
        }
    }

    @Override
    public List<ResponseUserLikes> getUserLikes(String userId) {


        List<ResponseUserLikes> list = new ArrayList<>();
        List<UserLikes> userLikesList =iUserLikesRepo.findAllByUserUserId(userId);
        for(int i=0;i<userLikesList.size();i++){
            Product product = iUserLikesRepo.findAllByUserUserId(userId).get(i).getProduct();
            ResponseUserLikes res = ResponseUserLikes.builder()
                    .likeCount(product.getLikeCount())
                    .name(product.getName())
                    .price(product.getPrice())
                    .productId(product.getId())
                    .productImage(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
                    .build();
            list.add(res);
        }
        for(int i=0;i<list.size();i++) log.info(list.get(i).getName());

        return list;
    }

    @Override
    public List<UserLikes> getAll() {

        return iUserLikesRepo.findAll();
    }
}
