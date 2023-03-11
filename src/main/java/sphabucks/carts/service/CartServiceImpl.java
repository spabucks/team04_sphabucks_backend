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
    public void addCart(RequestCart requestCart) {
        if(!iCartRepo.existsByProductId(requestCart.getProductId())){
            iCartRepo.save(Cart.builder()
                    .product(iProductRepository.findById(requestCart.getProductId()).get())
                    .user(iUserRepository.findByUserId(requestCart.getUserId()))
                    .categoryId(iProductCategoryListRepository.findAllByProductId(requestCart.getProductId()).get(0).getBigCategory().getId())
                    .amount(requestCart.getAmount())
                    .price(iProductRepository.findById(requestCart.getProductId()).get().getPrice())
                    .name(iProductRepository.findById(requestCart.getProductId()).get().getName())
                    .isDelete(false)
                    .build());
        }else{
            Cart cart = iCartRepo.findAllByProductId(requestCart.getProductId()).get(0);
            cart.setAmount(cart.getAmount() + requestCart.getAmount());
            cart.setIsDelete(false);
            iCartRepo.save(cart);
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
    public Cart updateCart(RequestCart requestCart) {
        Long userIdx = iUserRepository.findByUserId(requestCart.getUserId()).getId();
        Cart cart = iCartRepo.findAllByProductIdAndUserId(requestCart.getProductId(), userIdx).get(0);
        cart.setAmount(requestCart.getAmount());
        iCartRepo.save(cart);

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
