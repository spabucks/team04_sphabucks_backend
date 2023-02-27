package sphabucks.payments.gifticons.service;

import sphabucks.payments.gifticons.vo.RequestGiftIcon;
import sphabucks.payments.gifticons.vo.ResponseGiftIcon;

public interface IGiftIconService {
    void addGiftIcon(RequestGiftIcon requestGiftIcon);
    ResponseGiftIcon getGiftIcon(Long id);
}
