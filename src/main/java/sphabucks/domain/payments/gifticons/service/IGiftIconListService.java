package sphabucks.domain.payments.gifticons.service;

import sphabucks.domain.payments.gifticons.model.GiftIconList;
import sphabucks.domain.payments.gifticons.vo.RequestGiftIconList;
import sphabucks.global.auth.vo.RequestHead;

import java.util.List;

public interface IGiftIconListService {
    void addGiftIconList(RequestHead requestHead, RequestGiftIconList requestGiftIconList);
    List<GiftIconList> getGiftIconList(RequestHead requestHead);
}
