package sphabucks.domain.carts.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sphabucks.domain.carts.model.Cart;
import sphabucks.domain.carts.repository.ICartRepo;
import sphabucks.domain.carts.vo.*;
import sphabucks.domain.productimage.repository.IProductImageRepo;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.products.repository.IProductCategoryListRepository;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements ICartService{
    private final ICartRepo iCartRepo;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IProductImageRepo iProductImageRepo;

    @Override
    @Transactional
    public ResponseEntity<Object> addCart(RequestCart requestCart) {

        // 해당 상품이 고객의 장바구니에 담겼던 이력이 있는지 없는지
        if (iCartRepo.existsByUserUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId())) {    // 장바구니에 저장되었던 이력이 있다면
            // 해당하는 이력을 조회
            Cart cart = iCartRepo.findByUserUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId())
                    .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));

            if (cart.getAmount() + requestCart.getAmount() <= 5) {  // 한 상품을 최대 5개 까지 담을 수 있음
                // 기존에 있던 개수 + 새로 담는 개수를 저장함
                cart.setAmount(cart.getAmount() + requestCart.getAmount());
                // 상품이 장바구니에 추가되었으므로 isDelete = false
                cart.setIsDelete(false);
            } else {    // 기존에 담겨있던 개수 + 새로 담은 개수 > 5 인 경우
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((5 - cart.getAmount()));  // 담을 수 있는 최대 개수를 반환 (5 - 현재 장바구니에 담긴 개수)
            }
        } else { // 한 번도 장바구니에 추가되었던 이력이 없는 제품이라면

            Product product = iProductRepository.findById(requestCart.getProductId())
                    .orElseThrow(()->new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
            iCartRepo.save(Cart.builder()
                    .product(product)
                    .user(iUserRepository.findByUserId(requestCart.getUserId())
                            .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                    .categoryId(iProductCategoryListRepository.findByProductId(requestCart.getProductId()).getBigCategory().getId())
                    .amount(requestCart.getAmount())
                    .price(product.getPrice())
                    .name(product.getName())
                    .isDelete(false)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public List<ResponseGetCart> getCart(String userId) {  // userId : user.uuid

        List<ResponseGetCart> responseGetCartList = new ArrayList<>();

        if(iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId).isEmpty()){
            throw new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode());
        }

        // 고객의 장바구니 속 isDelete = false 인 제품들만 가져옴
        List<Cart> cartList = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId);

        cartList.forEach(cart -> responseGetCartList.add(ResponseGetCart.builder()
                .cartId(cart.getId())
                .productId(cart.getProduct().getId())
                .bigCategoryId(cart.getCategoryId())
                .count(cart.getAmount())
                .build()));
        return responseGetCartList;
    }

    @Override
    public ResponseGetCartProduct getCartProduct(Long productId) {
        Product product = iProductRepository.findById(productId)
                .orElseThrow(()->new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
        return ResponseGetCartProduct.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .imgUrl(iProductImageRepo.findAllByProductIdAndChk(productId, 1).get(0).getImage())
                .build();
    }

    @Override
    public List<ResponseCartV2> getCartV2(String userId) {
        // 유저의 모든 카트를 불러옴(삭제된 상품 카트는 불러오지 않음)
        List<Cart> cartList = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId);
        if (cartList.isEmpty()) {
            throw new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode());
        }

        List<ResponseCartV2> responseCartV2List = new ArrayList<>();
        cartList.forEach(cart -> {
            // 해당 상품 카트의 상품정보를 불러옴
            Product product = iProductRepository.findById(cart.getProduct().getId()).orElseThrow(() ->
                    new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));

            // 해당 상품의 이미지 정보(썸네일)를 불러옴
            String imgUrl = iProductImageRepo.findAllByProductIdAndChk(product.getId(), 1).get(0).getImage();
            responseCartV2List.add(ResponseCartV2.builder()
                    .cartId(cart.getId())
                    .productId(product.getId())
                    .bigCategoryId(cart.getCategoryId())
                    .count(cart.getAmount())
                    .productName(product.getName())
                    .imgUrl(imgUrl)
                    .price(product.getPrice())
                    .check(false)
                    .build());
        });

        return responseCartV2List;
    }

    @Override
    @Transactional
    public void updateCart(RequestUpdateCart request) {
        Cart cart = iCartRepo.findById(request.getCartId())
                .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));
        cart.setAmount(request.getAmount());
    }

    @Override
    @Transactional
    public void deleteCart(Long id) {
        Cart cart = iCartRepo.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));
        cart.setAmount(0L);
        cart.setIsDelete(true);
    }

    @Override
    @Transactional
    public void deleteSelectedCart(List<RequestDeleteSelectedCart> requestList) {
        requestList.forEach(request -> {
            Cart cart = iCartRepo.findById(request.getCartId())
                    .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));
            cart.setAmount(0L);
            cart.setIsDelete(true);
        });
    }

    @Override
    @Transactional
    public void deleteAll(String userId) {
        // userId(uuid) 에 연결된 장바구니 속 모든 정보 조회

        List<Cart> cartList = iCartRepo.findAllByUserId(iUserRepository.findByUserId(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                .getId());
        if(cartList.isEmpty()){
            throw new BusinessException(ErrorCode.CARTS_NOT_EXISTS, ErrorCode.CARTS_NOT_EXISTS.getCode());
        }
        for(Cart cart:cartList){
            cart.setAmount(0L);
            cart.setIsDelete(true);
        }
    }
}
