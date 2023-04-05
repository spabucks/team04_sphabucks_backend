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
    public ResponseEntity<Object> addCart(String userId, RequestCart requestCart) {

        if (iCartRepo.existsByUserUserIdAndProductId(userId, requestCart.getProductId())) {

            Cart cart = iCartRepo.findByUserUserIdAndProductId(userId, requestCart.getProductId())
                    .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));

            if (cart.getAmount() + requestCart.getAmount() <= 5) {
                cart.setAmount(cart.getAmount() + requestCart.getAmount());
                cart.setIsDelete(false);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((5 - cart.getAmount()));
            }
        } else {

            Product product = iProductRepository.findById(requestCart.getProductId())
                    .orElseThrow(()->new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
            iCartRepo.save(Cart.builder()
                    .product(product)
                    .user(iUserRepository.findByUserId(userId)
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
    public List<ResponseGetCart> getCart(String userId) {

        List<ResponseGetCart> responseGetCartList = new ArrayList<>();

        if(iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId).isEmpty()){
            throw new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode());
        }

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

        List<Cart> cartList = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId);
        List<ResponseCartV2> responseCartV2List = new ArrayList<>();

        cartList.forEach(cart -> {
            Product product = iProductRepository.findById(cart.getProduct().getId()).orElseThrow(() ->
                    new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));

            String imgUrl = iProductImageRepo.findAllByProductIdAndChk(product.getId(), 1).get(0).getImage();
            responseCartV2List.add(ResponseCartV2.builder()
                    .cartId(cart.getId())
                    .productId(product.getId())
                    .bigCategoryId(cart.getCategoryId())
                    .count(cart.getAmount())
                    .productName(product.getName())
                    .imgUrl(imgUrl)
                    .price(product.getPrice())
                    .check(true)
                    .build());
        });

        return responseCartV2List;
    }

    @Override
    @Transactional
    public void updateCart(RequestUpdateCart request) {
        Cart cart = iCartRepo.findById(request.getCartId())
                .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));

        if (cart.getIsDelete()) {
            throw new BusinessException(ErrorCode.DELETED_CART, ErrorCode.DELETED_CART.getCode());
        }

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
    public void deleteSelectedCart(RequestDeleteSelectedCart requestList) {
        requestList.getCartId().forEach(request -> {
            Cart cart = iCartRepo.findById(request)
                    .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));
            cart.setAmount(0L);
            cart.setIsDelete(true);
        });
    }

    @Override
    @Transactional
    public void deleteAll(String userId) {

        List<Cart> cartList = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId);


        if(cartList.isEmpty()){
            throw new BusinessException(ErrorCode.CARTS_NOT_EXISTS, ErrorCode.CARTS_NOT_EXISTS.getCode());
        }

        cartList.forEach(cart -> {
            cart.setIsDelete(true);
            cart.setAmount(0L);
        });
    }


    @Override
    @Transactional
    public ResponseEntity<Object> addCartFromPurchase(String userId, RequestCart requestCart) {

        if (iCartRepo.existsByUserUserIdAndProductId(userId, requestCart.getProductId())) {
            // 해당 상품이 카트에 있으면

            Cart cart = iCartRepo.findByUserUserIdAndProductId(userId, requestCart.getProductId())
                    .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));

            if (requestCart.getAmount() <= 5) {
                cart.setAmount(requestCart.getAmount());
                cart.setIsDelete(false);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((5 - cart.getAmount()));
            }
        } else {
            // 없으면

            Product product = iProductRepository.findById(requestCart.getProductId())
                    .orElseThrow(()->new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS, ErrorCode.PRODUCT_NOT_EXISTS.getCode()));
            iCartRepo.save(Cart.builder()
                    .product(product)
                    .user(iUserRepository.findByUserId(userId)
                            .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                    .categoryId(iProductCategoryListRepository.findByProductId(requestCart.getProductId()).getBigCategory().getId())
                    .amount(requestCart.getAmount())
                    .price(product.getPrice())
                    .name(product.getName())
                    .isDelete(false)
                    .build());
        }

        Long cartId = iCartRepo.findAllByUserUserIdOrderByUpdateDateDesc(userId).get(0).getId();

        return ResponseEntity.status(HttpStatus.OK).body(cartId);
    }



}
