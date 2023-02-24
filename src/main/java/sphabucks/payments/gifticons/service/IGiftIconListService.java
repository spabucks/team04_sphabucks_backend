package sphabucks.payments.gifticons.service;

import sphabucks.payments.gifticons.model.GiftIconList;
import sphabucks.payments.gifticons.vo.RequestGiftIconList;

import java.util.List;

public interface IGiftIconListService {
    void addGiftIconList(RequestGiftIconList requestGiftIconList);
    List<GiftIconList> getGiftIconList(Long id);
}
