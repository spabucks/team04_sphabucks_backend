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

        User user = iUserRepository.findByUserId(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        if (iUserWishlistRepo.existsByUserUserIdAndProductId(userId, request.getProductId())) {
            log.info("db update");
            UserWishlist wishlist = iUserWishlistRepo.findByUserIdAndProductId(user.getId(), request.getProductId());
            wishlist.setIsDeleted(!wishlist.getIsDeleted());
        } else {
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
