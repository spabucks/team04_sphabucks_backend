package sphabucks.domain.purchaseHistory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import sphabucks.domain.carts.repository.ICartRepo;
import sphabucks.domain.purchaseHistory.model.PurchaseHistory;
import sphabucks.domain.purchaseHistory.vo.IResponsePaymentNum;
import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseHistory;
import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseHistoryList;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;

import sphabucks.domain.carts.model.Cart;
import sphabucks.domain.productimage.repository.IProductImageRepo;

import sphabucks.domain.purchaseHistory.repository.IPurchaseHistoryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseHistoryServiceImpl implements IPurchaseHistoryService{

    private final IPurchaseHistoryRepository iPurchaseHistoryRepository;
    private final IUserRepository iUserRepository;
    private final IProductImageRepo iProductImageRepo;
    private final ICartRepo iCartRepo;

    @Override
    public void addPurchaseHistory(List<Long> selected, String userId) {
        // 현재
        // selected : 구매내역 (구매할 상품 cart id) 체크리스트 배열로 받는다고 생각하고 구현.
        // 전체 배열 돌면서 값 저장하도록 되어있음.
        // 후에 프론트에서 값 넘겨주는거 보고 수정할 예정

        // 주문번호 생성
        String paymentNum = createPaymentNum();
        if(iPurchaseHistoryRepository.findByPaymentNum(paymentNum).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_HISTORY, ErrorCode.DUPLICATE_HISTORY.getCode());
        }

        for (int i = 0; i < selected.size(); i++) {
            Cart cart = iCartRepo.findById(selected.get(i))
                    .orElseThrow(()-> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));

            PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                    .user(iUserRepository.findByUserId(userId)
                            .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
                    .image(iProductImageRepo.findAllByProductId(
                            cart.getProduct().getId()
                            ).get(0).getImage())
                    .amount(cart.getAmount())
                    .sum(
                            cart.getPrice() * cart.getAmount()
                    )
                    .paymentNum(paymentNum)
                    .type(false)
                    .spStatus("1")
                    .orStatus("1")
                    .productName(cart.getProduct().getName())
                    .build();

            iPurchaseHistoryRepository.save(purchaseHistory);
        }
    }

    @Override
    public List<ResponsePurchaseHistoryList> getPurchaseHistoryList(String userId) {

        log.info("@@@@@@@@@@@@@@@@@@@@@@@1");

        // userId에 해당하는 paymentNum 모두 조회
        List<IResponsePaymentNum> paymentNumList = iPurchaseHistoryRepository.findAllPaymentNum(
                iUserRepository.findByUserId(userId)
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                        .getId()
        );
        if(paymentNumList.isEmpty()){
            throw new BusinessException(ErrorCode.HISTORY_NOT_EXISTS, ErrorCode.HISTORY_NOT_EXISTS.getCode());
        }

        log.info("@@@@@@@@@@@@@@@@@@@@@@@2 {}",paymentNumList);

        // 리턴할 리스트
        List<ResponsePurchaseHistoryList> result = new ArrayList<>();
        
        // 조회한 paymentNum로 조회하면서 list에 결과 저장
        paymentNumList.forEach(paymentNum -> {
            List<PurchaseHistory> purchaseHistoryList = iPurchaseHistoryRepository.findAllByPaymentNum(
                    iUserRepository.findByUserId(userId)
                            .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                            .getId(), paymentNum.getPaymentNum()
            );

            List<ResponsePurchaseHistory> tmp2 = new ArrayList<>();



            // purchaseHistory -> responsePurchaseHistory 옮겨닮기
            purchaseHistoryList.forEach(purchaseHistory -> {
                ResponsePurchaseHistory responsePurchaseHistory = ResponsePurchaseHistory.builder()
                        .id(purchaseHistory.getId())
                        .productName(purchaseHistory.getProductName())
                        .amount(purchaseHistory.getAmount())
                        .sum(purchaseHistory.getSum())
                        .type(purchaseHistory.getType())
                        .paymentNum(purchaseHistory.getPaymentNum())
                        .orderDate(purchaseHistory.getCreateDate())
                        .sp_status(purchaseHistory.getSpStatus())
                        .or_status(purchaseHistory.getOrStatus())
                        .image(purchaseHistory.getImage())
                        .build();

                tmp2.add(responsePurchaseHistory);

            });
            
            // ResponsePurchaseHistoryList 객체 생성 후 List<ResponsePurchaseHistoryList>에 저장
            if (purchaseHistoryList.size() > 1) {
                ResponsePurchaseHistoryList responsePurchaseHistoryList = ResponsePurchaseHistoryList.builder()
                        .id(purchaseHistoryList.get(0).getId())
                        .orderName(
                                purchaseHistoryList.get(0).getProductName() + " 외 "
                                        + (purchaseHistoryList.size()-1) +" 상품")
                        .amount(paymentNum.getAmount())
                        .sum(paymentNum.getSum())
                        .paymentNum(purchaseHistoryList.get(0).getPaymentNum())
                        .orderDate(purchaseHistoryList.get(0).getCreateDate())
                        .sp_status(purchaseHistoryList.get(0).getSpStatus())
                        .or_status(purchaseHistoryList.get(0).getOrStatus())
                        .image(purchaseHistoryList.get(0).getImage())
                        .list(tmp2)
                        .build();

                result.add(responsePurchaseHistoryList);
            } else {
                ResponsePurchaseHistoryList responsePurchaseHistoryList = ResponsePurchaseHistoryList.builder()
                        .id(purchaseHistoryList.get(0).getId())
                        .orderName(
                                purchaseHistoryList.get(0).getProductName())
                        .amount(paymentNum.getAmount())
                        .sum(paymentNum.getSum())
                        .paymentNum(purchaseHistoryList.get(0).getPaymentNum())
                        .orderDate(purchaseHistoryList.get(0).getCreateDate())
                        .sp_status(purchaseHistoryList.get(0).getSpStatus())
                        .or_status(purchaseHistoryList.get(0).getOrStatus())
                        .image(purchaseHistoryList.get(0).getImage())
                        .list(tmp2)
                        .build();

                result.add(responsePurchaseHistoryList);
            }

        });

        return result;
    }

    // 주문번호 생성 (ex) 230315(날짜)-xxxxxx(난수 6자리)
    private String createPaymentNum() {
        StringBuffer sb = new StringBuffer();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        sb.append(currentDate.format(dateTimeFormatter));
        sb.append("-");
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            sb.append((random.nextInt(10)));
        }

        return sb.toString();
    }
}
