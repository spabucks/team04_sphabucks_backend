package sphabucks.carts.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.carts.model.Cart;
import sphabucks.carts.repository.ICartRepo;
import sphabucks.carts.vo.RequestCart;
import sphabucks.carts.vo.ResponseCart;
import sphabucks.carts.vo.ResponseCartSummary;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.products.model.Product;
import sphabucks.products.repository.IProductCategoryListRepository;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.User;
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
    public void addCart(RequestCart requestCart) {

        // 해당 상품이 고객의 장바구니에 담겼던 이력이 있는지 없는지
        if (iCartRepo.existsByUserUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId())) {    // 장바구니에 저장되었던 이력이 있다면
            // 해당하는 이력을 조회
            Cart cart = iCartRepo.findByUserUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId());
            // 기존에 있던 개수 + 새로 담는 개수를 저장함
            cart.setAmount(cart.getAmount() + requestCart.getAmount());
            // 상품이 장바구니에 추가되었으므로 isDelete = false
            cart.setIsDelete(false);
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
    }

    @Override
    public List<ResponseCart> getCart(String userId) {  // userId : user.uuid

        // 고객의 장바구니 속 isDelete = false 인 제품들만 가져옴
        List<Cart> cartList = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(userId);

        List<ResponseCartSummary> cartProductFreeze = new ArrayList<>();    // 냉동 상품(케이크) 상품 정보를 담을 리스트
        List<ResponseCartSummary> cartProductGeneral = new ArrayList<>();    // 일반 상품 정보를 담을 리스트

        cartList.forEach(cart -> {
            Long bigCategoryId = cart.getCategoryId();    // 카드에 담긴 상품의 대분류 카테고리 Id
            if (bigCategoryId == 1) {   // 현재 케이크가 id 1번
                cartProductFreeze.add(ResponseCartSummary.builder()
                                .productId(cart.getProduct().getId())
                                .productName(cart.getProduct().getName())
                                .imgUrl(iProductImageRepo.findAllByProductIdAndChk(cart.getProduct().getId(), 1).get(0).getImage())
                                .price(cart.getPrice())
                                .count(cart.getAmount())
                        .build());
            } else {
                cartProductGeneral.add(ResponseCartSummary.builder()
                        .productId(cart.getProduct().getId())
                        .productName(cart.getProduct().getName())
                        .imgUrl(iProductImageRepo.findAllByProductIdAndChk(cart.getProduct().getId(), 1).get(0).getImage())
                        .price(cart.getPrice())
                        .count(cart.getAmount())
                        .build());
            }
        });

        List<ResponseCart> responseCartList = new ArrayList<>();

        responseCartList.add(ResponseCart.builder()
                .categoryName("cartProductFreeze")
                .responseCartSummaryList(cartProductFreeze)
                .build());

        responseCartList.add(ResponseCart.builder()
                .categoryName("cartProductGeneral")
                .responseCartSummaryList(cartProductGeneral)
                .build());

        return responseCartList;
    }

    @Override
    @Transactional
    public Cart updateCart(RequestCart requestCart) {
        Cart cart = iCartRepo.findByUserUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId());
        cart.setAmount(requestCart.getAmount());
        return cart;
    }

    @Override
    @Transactional
    public void deleteCart(RequestCart requestCart) {
        List<Cart> cartlist = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(requestCart.getUserId());
        Cart cart = null;
        for(int i=0;i<cartlist.size();i++){
            if(cartlist.get(i).getProduct().getId().equals(requestCart.getProductId())){
                cart = cartlist.get(i);
            }
        }
        if (cart != null) {
            cart.setIsDelete(true);
        }
    }

    @Override
    @Transactional
    public void deleteAll(RequestCart requestCart) {
        List<Cart> cartList = iCartRepo.findAllByUserUserIdAndIsDeleteIsFalse(requestCart.getUserId());
        for(Cart cart:cartList){
            cart.setIsDelete(true);
        }
    }
}
