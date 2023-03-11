package sphabucks.carts.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sphabucks.carts.model.Cart;
import sphabucks.carts.repository.ICartRepo;
import sphabucks.carts.vo.RequestCart;
import sphabucks.carts.vo.ResponseCart;
import sphabucks.carts.vo.ResponseCategoryList;
import sphabucks.payments.cards.vo.ResponseCard;
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
    public List<Cart> getCart(String userId) {
        // userId 를 통해 카드에 들어잇는 상품들의 리스트를 받아온다.
        List<Cart> cartList = iCartRepo.findAllByUserId(iUserRepository.findByUserId(userId).getId());

        // List 화 해서 가져와야한다.
        List<ResponseCart> responseCartList = new ArrayList<>();

        // 카테고리가 케이크인지 아닌지
        // product 별로 개수 출력
        // isDelete가 true가 아닌 것들만 출력
        return cartList;
    }

    @Override
    public Cart updateCart(RequestCart requestCart) {
        Cart cart = iCartRepo.findAllByProductIdAndUserId(requestCart.getProductId(), requestCart.getUserId()).get(0);
        cart.setAmount(requestCart.getAmount());
        iCartRepo.save(cart);

        return cart;
    }

    @Override
    @Transactional
    public void deleteCart(RequestCart requestCart) {
        List<Cart> cartlist = iCartRepo.findAllByUserId(iUserRepository.findByUserId(requestCart.getUserId()).getId());
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
        List<Cart> cartList = iCartRepo.findAllByUserId(iUserRepository.findByUserId(requestCart.getUserId()).getId());
        for(Cart cart:cartList){
            cart.setIsDelete(true);
        }
    }


}
