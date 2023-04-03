package sphabucks.domain.purchaseHistory.service;

import sphabucks.domain.purchaseHistory.model.PurchaseTmp;
import sphabucks.domain.purchaseHistory.vo.RequestPurchaseTmp;
import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseTmp;

import java.util.List;

public interface IPurchaseTmpService {

    void addPurchaseTmp(String userId, List<Long> cartList);

    List<ResponsePurchaseTmp> getPurchaseTmp(String userId);

}
