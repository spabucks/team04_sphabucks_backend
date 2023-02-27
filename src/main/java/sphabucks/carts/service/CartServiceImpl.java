package sphabucks.carts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sphabucks.carts.model.Cart;
import sphabucks.carts.repository.ICartRepo;
import sphabucks.carts.vo.RequestCart;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService{
    private final ICartRepo iCartRepo;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;

    @Override
    public void addCart(RequestCart requestCart) {
        iCartRepo.save(Cart.builder()
                        .user(iUserRepository.findById(requestCart.getUserId()).get())
                        .product(iProductRepository.findById(requestCart.getProductId()).get())
                .build());
    }

    @Override
    public List<Cart> getCart(Long userId) {
        return iCartRepo.findAllByUserId(userId);
    }
}
