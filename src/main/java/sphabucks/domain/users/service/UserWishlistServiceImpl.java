package sphabucks.domain.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.users.model.UserWishlist;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.domain.users.repository.IUserWishlistRepo;
import sphabucks.domain.users.vo.RequestUserWishlist;
import sphabucks.domain.users.vo.ResponseWishList;
import sphabucks.domain.users.vo.ResponseWishListProduct;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.productimage.repository.IProductImageRepo;
import sphabucks.domain.users.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserWishlistServiceImpl implements IUserWishlistService {
    private final IUserWishlistRepo iUserWishlistRepo;
    private final IProductRepository iProductRepository;
    private final IUserRepository iUserRepository;
    private final IProductImageRepo iProductImageRepo;


    @Override
    @Transactional
    public void clickWishList(String userId, RequestUserWishlist request) {

        // uuid 를 이용하여 조회한 user
        User user = iUserRepository.findByUserId(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        // 해당 유저가 상품을 위시리스트에 추가했던 내역이 있었다면
        if (iUserWishlistRepo.existsByUserUserIdAndProductId(userId, request.getProductId())) {
            log.info("db update");
            UserWishlist wishlist = iUserWishlistRepo.findByUserIdAndProductId(user.getId(), request.getProductId());
            wishlist.setIsDeleted(!wishlist.getIsDeleted());    // 기존의 정보와 반대로 저장
        } else { // 위시 리스트에 추가 되었던 내역이 없다면
            log.info("new data");
            iUserWishlistRepo.save(UserWishlist.builder()
                            .user(user)
                            .product(iProductRepository.findById(request.getProductId())
                                    .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode())))
                            .isDeleted(false)
                    .build());
        }
    }

    @Override
    public List<ResponseWishList> getByUserWishlist(String userId) {
        List<ResponseWishList> responseWishLists = new ArrayList<>();

        if(iUserWishlistRepo.findAllByUserUserIdAndIsDeletedIsFalse(userId).isEmpty()){
            throw new BusinessException(ErrorCode.WISHLIST_NOT_EXISTS, ErrorCode.WISHLIST_NOT_EXISTS.getCode());
        }

        iUserWishlistRepo.findAllByUserUserIdAndIsDeletedIsFalse(userId).forEach(userWishlist ->
            responseWishLists.add(ResponseWishList.builder()
                    .id(userWishlist.getId())
                    .productId(userWishlist.getProduct().getId())
                    .build()));

        return responseWishLists;
    }

    @Override
    public ResponseWishListProduct getWishListProduct(Long productId) {
        Product product = iProductRepository.findById(productId)
                .orElseThrow(()-> new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
        return ResponseWishListProduct.builder()
                .title(product.getName())
                .price(product.getPrice())
                .imgUrl(iProductImageRepo.findAllByProductId(productId).get(0).getImage())
                .build();
    }
}
