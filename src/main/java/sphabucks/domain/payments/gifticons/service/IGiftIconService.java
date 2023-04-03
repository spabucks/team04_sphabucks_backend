package sphabucks.domain.payments.gifticons.service;

import sphabucks.domain.payments.gifticons.vo.RequestGiftIcon;
import sphabucks.domain.payments.gifticons.vo.ResponseGiftIcon;

public interface IGiftIconService {
    void addGiftIcon(RequestGiftIcon requestGiftIcon);
    ResponseGiftIcon getGiftIcon(Long id);
}
