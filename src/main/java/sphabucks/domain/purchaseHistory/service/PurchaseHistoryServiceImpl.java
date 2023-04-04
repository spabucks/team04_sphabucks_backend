package sphabucks.domain.purchaseHistory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import sphabucks.domain.carts.repository.ICartRepo;
import sphabucks.domain.carts.service.ICartService;
import sphabucks.domain.purchaseHistory.model.PurchaseHistory;
import sphabucks.domain.purchaseHistory.model.PurchaseTmp;
import sphabucks.domain.purchaseHistory.repository.IPurchaseTmpRepository;
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
    private final IPurchaseTmpRepository iPurchaseTmpRepository;
    private final ICartService iCartService;

    @Override
    @Transactional
    public void addPurchaseHistory(String userId) {

        String paymentNum = createPaymentNum();
        if(iPurchaseHistoryRepository.findByPaymentNum(paymentNum).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_HISTORY, ErrorCode.DUPLICATE_HISTORY.getCode());
        }

        if(iPurchaseTmpRepository.findAllByUserId(userId).isEmpty()) {
            throw new BusinessException(ErrorCode.PURCHASE_TMP_NOT_EXISTS,ErrorCode.PURCHASE_TMP_NOT_EXISTS.getCode());
        }
        List<PurchaseTmp> list = iPurchaseTmpRepository.findAllByUserId(userId);
        List<Long> selected = new ArrayList<>();
        list.forEach(item -> {
            selected.add(item.getCart().getId());
        });

        for (Long aLong : selected) {
            Cart cart = iCartRepo.findById(aLong)
                    .orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_EXISTS, ErrorCode.CART_NOT_EXISTS.getCode()));

            PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                    .user(iUserRepository.findByUserId(userId)
                            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode())))
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
            iCartService.deleteCart(aLong);

        }
        iPurchaseTmpRepository.deletePurchaseTmp(userId);
    }

    @Override
    public List<ResponsePurchaseHistoryList> getPurchaseHistoryListAll(String userId) {


        List<IResponsePaymentNum> paymentNumList = iPurchaseHistoryRepository.findAllPaymentNum(
                iUserRepository.findByUserId(userId)
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                        .getId()
        );



        List<ResponsePurchaseHistoryList> result = new ArrayList<>();

        if(!paymentNumList.isEmpty()){
            paymentNumList.forEach(paymentNum -> {
                List<PurchaseHistory> purchaseHistoryList = iPurchaseHistoryRepository.findAllByPaymentNum(
                        iUserRepository.findByUserId(userId)
                                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                                .getId(), paymentNum.getPaymentNum()
                );

                List<ResponsePurchaseHistory> tmp2 = new ArrayList<>();


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

                ResponsePurchaseHistoryList responsePurchaseHistoryList;
                if (purchaseHistoryList.size() > 1) {
                    responsePurchaseHistoryList = ResponsePurchaseHistoryList.builder()
                            .id(purchaseHistoryList.get(0).getId())
                            .orderName(
                                    purchaseHistoryList.get(0).getProductName() + " 외 "
                                            + (purchaseHistoryList.size() - 1) + " 상품")
                            .amount(paymentNum.getAmount())
                            .sum(paymentNum.getSum())
                            .paymentNum(purchaseHistoryList.get(0).getPaymentNum())
                            .orderDate(purchaseHistoryList.get(0).getCreateDate())
                            .sp_status(purchaseHistoryList.get(0).getSpStatus())
                            .or_status(purchaseHistoryList.get(0).getOrStatus())
                            .image(purchaseHistoryList.get(0).getImage())
                            .list(tmp2)
                            .build();

                } else {
                    responsePurchaseHistoryList = ResponsePurchaseHistoryList.builder()
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

                }
                result.add(responsePurchaseHistoryList);

            });
        }

        return result;
    }

    @Override
    public List<ResponsePurchaseHistoryList> getPurchaseHistoryListOne(String userId) {

        PurchaseHistory purchaseHistoryOne = iPurchaseHistoryRepository.findAllByUserIdOrderByIdDesc(
                iUserRepository.findByUserId(userId).get().getId()).get(0);

        log.info("1111111111111111111111111111111 {}", purchaseHistoryOne.getPaymentNum());

        List<IResponsePaymentNum> paymentNumList = iPurchaseHistoryRepository.findRecentPaymentNum(
                iUserRepository.findByUserId(userId)
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                        .getId(),
                purchaseHistoryOne.getPaymentNum()
        );

        log.info("222222222222222222222222222222 {}", paymentNumList.get(0).getPaymentNum());
        log.info("222222222222222222222222222222 {}", paymentNumList.get(0).getAmount());
        log.info("222222222222222222222222222222 {}", paymentNumList.get(0).getSum());


        List<ResponsePurchaseHistoryList> result = new ArrayList<>();

        if(!paymentNumList.isEmpty()){
            paymentNumList.forEach(paymentNum -> {
                List<PurchaseHistory> purchaseHistoryList = iPurchaseHistoryRepository.findAllByPaymentNum(
                        iUserRepository.findByUserId(userId)
                                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                                .getId(), paymentNum.getPaymentNum()
                );

                List<ResponsePurchaseHistory> tmp2 = new ArrayList<>();


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

                ResponsePurchaseHistoryList responsePurchaseHistoryList;
                if (purchaseHistoryList.size() > 1) {
                    responsePurchaseHistoryList = ResponsePurchaseHistoryList.builder()
                            .id(purchaseHistoryList.get(0).getId())
                            .orderName(
                                    purchaseHistoryList.get(0).getProductName() + " 외 "
                                            + (purchaseHistoryList.size() - 1) + " 상품")
                            .amount(paymentNum.getAmount())
                            .sum(paymentNum.getSum())
                            .paymentNum(purchaseHistoryList.get(0).getPaymentNum())
                            .orderDate(purchaseHistoryList.get(0).getCreateDate())
                            .sp_status(purchaseHistoryList.get(0).getSpStatus())
                            .or_status(purchaseHistoryList.get(0).getOrStatus())
                            .image(purchaseHistoryList.get(0).getImage())
                            .list(tmp2)
                            .build();

                } else {
                    responsePurchaseHistoryList = ResponsePurchaseHistoryList.builder()
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

                }
                result.add(responsePurchaseHistoryList);

            });
        }

        return result;
    }

    private String createPaymentNum() {
        StringBuilder sb = new StringBuilder();
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
