package sphabucks.domain.payments.gifticons.service;

import sphabucks.domain.payments.gifticons.model.GiftIconList;
import sphabucks.domain.payments.gifticons.vo.RequestGiftIconList;

import java.util.List;

public interface IGiftIconListService {
    void addGiftIconList(String userId, RequestGiftIconList requestGiftIconList);
    List<GiftIconList> getGiftIconList(String userId);
}
