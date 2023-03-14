package sphabucks.carts.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.carts.model.Cart;
import sphabucks.carts.repository.ICartRepo;
import sphabucks.carts.vo.RequestCart;
import sphabucks.carts.vo.ResponseGetCart;
import sphabucks.carts.vo.ResponseGetCartProduct;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductCategoryListRepository;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService{
    private final ICartRepo iCartRepo;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IProductImageRepo iProductImageRepo;

    @Override
    @Transactional
    public Long addCart(RequestCart requestCart) {

        // 해당 상품이 고객의 장바구니에 담겼던 이력이 있는지 없는지
        if (iCartRepo.existsByUserUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId())) {    // 장바구니에 저장되었던 이력이 있다면
            // 해당하는 이력을 조회
            Cart cart = iCartRepo.findByUserUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId());
            if (cart.getAmount() + requestCart.getAmount() <= 5) {  // 한 상품을 최대 5개 까지 담을 수 있음
                // 기존에 있던 개수 + 새로 담는 개수를 저장함
                cart.setAmount(cart.getAmount() + requestCart.getAmount());
                // 상품이 장바구니에 추가되었으므로 isDelete = false
                cart.setIsDelete(false);
            } else {    // 기존에 담겨있던 개수 + 새로 담은 개수 > 5 인 경우
                return (5 - cart.getAmount());  // 담을 수 있는 최대 개수를 반환 (5 - 현재 장바구니에 담긴 개수)
            }
        } else { // 한 번도 장바구니에 추가되었던 이력이 없는 제품이라면
            Product product = iProductRepository.findById(requestCart.getProductId()).get();
            iCartRepo.save(Cart.builder()
                    .product(product)
                    .user(iUserRepository.findByUserId(requestCart.getUserId()))
                    .categoryId(iProductCategoryListRepository.findByProductId(requestCart.getProductId()).getBigCategory().getId())
                    .amount(requestCart.getAmount())
                    .price(product.getPrice())
                    .name(product.getName())
                    .isDelete(false)
                    .build());
        }
        return 200L;
    }

    @Override
    public List<ResponseGetCart> getCart(String userId) {  // userId : user.uuid

        List<ResponseGetCart> responseGetCartList = new ArrayList<>();
        // 고객의 장바구니 속 isDelete = false 인 제품들만 가져옴
        List<Cart> cartList = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId);

        cartList.forEach(cart -> {
            responseGetCartList.add(ResponseGetCart.builder()
                    .cartId(cart.getId())
                    .productId(cart.getProduct().getId())
                    .bigCategoryId(cart.getCategoryId())
                    .count(cart.getAmount())
                    .build());
        });
        return responseGetCartList;
    }

    @Override
    public ResponseGetCartProduct getCartProduct(Long productId) {
        Product product = iProductRepository.findById(productId).get();
        return ResponseGetCartProduct.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .imgUrl(iProductImageRepo.findAllByProductIdAndChk(productId, 1).get(0).getImage())
                .build();
    }

    @Override
    @Transactional
    public void updateCart(Long productId, RequestCart requestCart) {
//        iCartRepo.findAllByUserId(id).get(Math.toIntExact(requestCart.getProductId())).setAmount(requestCart.getAmount());
        iCartRepo.updateAmount(requestCart.getAmount(), productId);
    }

    @Override
    @Transactional
    public void deleteCart(Long id) {
        Cart cart = iCartRepo.findById(id).get();
        cart.setAmount(0L);
        cart.setIsDelete(true);
    }

    @Override
    @Transactional
    public void deleteAll(String userId) {
        // userId(uuid) 에 연결된 장바구니 속 모든 정보 조회
        List<Cart> cartList = iCartRepo.findAllByUserId(iUserRepository.findByUserId(userId).getId());
        for(Cart cart:cartList){
            cart.setAmount(0L);
            cart.setIsDelete(true);
        }
    }
}
