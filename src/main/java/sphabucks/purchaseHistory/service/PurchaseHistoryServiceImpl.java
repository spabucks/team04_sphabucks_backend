package sphabucks.purchaseHistory.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.carts.model.Cart;
import sphabucks.carts.repository.ICartRepo;
import sphabucks.productimage.repository.IProductImageRepo;
import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.repository.IPurchaseHistoryRepository;
import sphabucks.purchaseHistory.vo.RequestPurchaseHistory;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.service.UserServiceImplement;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryServiceImpl implements IPurchaseHistoryService{

    private final IPurchaseHistoryRepository iPurchaseHistoryRepository;
    private final IUserRepository iUserRepository;
    private final IProductImageRepo iProductImageRepo;
    private final ICartRepo iCartRepo;

    @Override
    public void addPurchaseHistory(List<Long> selected, String userId) {
        // 현재
        // selected : 구매내역 (구매할 상품 cart id) 체크리스트 배열로 받는다고 생각하고 구현.
        // 체크한 값 true false에 따라 처리는 구현 안되어있고, 전체 배열 돌면서 값 저장하도록 되어있음.
        // 후에 프론트에서 값 넘겨주는거 보고 수정할 예정

        // 주문번호 생성
        StringBuffer paymentNum = new StringBuffer();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        paymentNum.append(currentDate.format(dateTimeFormatter));
        paymentNum.append("-");
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            paymentNum.append((random.nextInt(10)));
        }

        for (int i = 0; i < selected.size(); i++) {
            Cart cart = iCartRepo.findById(selected.get(i)).get();

            PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                    .user(iUserRepository.findByUserId(userId))
                    .image(iProductImageRepo.findAllByProductId(
                            cart.getProduct().getId()
                            ).get(0).getImage())
                    .amount(cart.getAmount())
                    .sum(
                            cart.getPrice() * cart.getAmount()
                    )
                    .payment_num(paymentNum.toString())
                    .type(false)
                    .sp_status("1")
                    .or_status("1")
                    .productName(cart.getProduct().getName())
                    .build();

            iPurchaseHistoryRepository.save(purchaseHistory);
        }
    }

    @Override
    public PurchaseHistory getPurchaseHistory(Long id) {
        return iPurchaseHistoryRepository.findById(id).get();
    }
}
