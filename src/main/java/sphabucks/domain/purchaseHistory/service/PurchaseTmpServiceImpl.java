package sphabucks.domain.purchaseHistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sphabucks.domain.carts.model.Cart;
import sphabucks.domain.carts.repository.ICartRepo;
import sphabucks.domain.productimage.repository.IProductImageRepo;
import sphabucks.domain.products.model.Product;
import sphabucks.domain.products.repository.IProductRepository;
import sphabucks.domain.purchaseHistory.model.PurchaseTmp;
import sphabucks.domain.purchaseHistory.repository.IPurchaseTmpRepository;
import sphabucks.domain.purchaseHistory.vo.RequestPurchaseTmp;
import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseTmp;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseTmpServiceImpl implements IPurchaseTmpService {

    private final ICartRepo iCartRepo;
    private final IPurchaseTmpRepository iPurchaseTmpRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IProductImageRepo iProductImageRepo;

    @Override
    @Transactional
    public void addPurchaseTmp(String userId, List<Long> cartList) {

        if (iUserRepository.findByUserId(userId).isEmpty()){
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode());
        };

        iPurchaseTmpRepository.deletePurchaseTmp(userId);

        cartList.forEach(item -> {
            if (iCartRepo.findByIdAndUserUserId(item, userId).isEmpty()) {
                throw new BusinessException(ErrorCode.CARD_NOT_EXISTS,ErrorCode.CARTS_NOT_EXISTS.getCode());
            }
            if (iPurchaseTmpRepository.findByCartIdAndUserId(item, userId).isEmpty()) {
                PurchaseTmp purchaseTmp = PurchaseTmp.builder()
                        .cart(iCartRepo.findByIdAndUserUserId(item, userId).get())
                        .userId(userId)
                        .build();
                iPurchaseTmpRepository.save(purchaseTmp);
            }

        });
    }

    @Override
    public List<ResponsePurchaseTmp> getPurchaseTmp(String userId) {

        List<PurchaseTmp> tmpList = iPurchaseTmpRepository.findByUserId(userId);

        List<ResponsePurchaseTmp> result = new ArrayList<>();

        tmpList.forEach(item -> {

            Product product = iProductRepository.findById(item.getCart().getProduct().getId()).get();

            ResponsePurchaseTmp responsePurchaseTmp = ResponsePurchaseTmp.builder()
                    .cartId(item.getCart().getId())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .count(item.getCart().getAmount())
                    .imgUrl(iProductImageRepo.findAllByProductId(product.getId()).get(0).getImage())
                    .build();

            result.add(responsePurchaseTmp);
        });

        return result;
    }
}
