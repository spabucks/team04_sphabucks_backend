package sphabucks.domain.purchaseHistory.service;

import sphabucks.domain.purchaseHistory.model.PurchaseTmp;
import sphabucks.domain.purchaseHistory.vo.RequestPurchaseTmp;
import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseTmp;

import java.util.List;

public interface IPurchaseTmpService {

    void addPurchaseTmp(String userId, RequestPurchaseTmp requestPurchaseTmp);

    void addPurchaseTmpOne(String userId, Long cartId);

    List<ResponsePurchaseTmp> getPurchaseTmp(String userId);

}
