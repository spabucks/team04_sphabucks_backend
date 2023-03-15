package sphabucks.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.User;
import sphabucks.users.model.UserWishlist;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.repository.IUserWishlistRepo;
import sphabucks.users.vo.RequestUserWishlist;
import sphabucks.users.vo.ResponseWishList;
import sphabucks.users.vo.ResponseWishListProduct;

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
    public void clickWishList(RequestUserWishlist request) {

        // uuid 를 이용하여 조회한 user
        User user = iUserRepository.findByUserId(request.getUserId());

        // 해당 유저가 상품을 위시리스트에 추가했던 내역이 있었다면
        if (iUserWishlistRepo.existsByUserUserIdAndProductId(request.getUserId(), request.getProductId())) {
            log.info("db update");
            UserWishlist wishlist = iUserWishlistRepo.findByUserIdAndProductId(user.getId(), request.getProductId());
            wishlist.setIsDeleted(!wishlist.getIsDeleted());    // 기존의 정보와 반대로 저장
        } else { // 위시 리스트에 추가 되었던 내역이 없다면
            log.info("new data");
            iUserWishlistRepo.save(UserWishlist.builder()
                            .user(user)
                            .product(iProductRepository.findById(request.getProductId()).get())
                            .isDeleted(false)
                    .build());
        }
    }

    @Override
    public List<ResponseWishList> getByUserWishlist(String userId) {
        List<ResponseWishList> responseWishLists = new ArrayList<>();
        iUserWishlistRepo.findAllByUserUserIdAndIsDeletedIsFalse(userId).forEach(userWishlist -> {
            responseWishLists.add(ResponseWishList.builder()
                    .id(userWishlist.getId())
                    .productId(userWishlist.getProduct().getId())
                    .build());
        });
        return responseWishLists;
    }

    @Override
    public ResponseWishListProduct getWishListProduct(Long productId) {
        Product product = iProductRepository.findById(productId).get();
        return ResponseWishListProduct.builder()
                .title(product.getName())
                .price(product.getPrice())
                .imgUrl(iProductImageRepo.findAllByProductId(productId).get(0).getImage())
                .build();
    }
}
