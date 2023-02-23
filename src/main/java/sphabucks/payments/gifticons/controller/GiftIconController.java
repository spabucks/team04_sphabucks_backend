package sphabucks.payments.gifticons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.gifticons.model.GiftIcon;
import sphabucks.payments.gifticons.service.IGiftIconService;
import sphabucks.payments.gifticons.vo.RequestGiftIcon;
import sphabucks.payments.gifticons.vo.ResponseGiftIcon;

@RestController
@RequestMapping("/gift-icons")
@RequiredArgsConstructor
public class GiftIconController {
    private final IGiftIconService iGiftIconService;

    @PostMapping("/add")
    public ResponseGiftIcon addGiftIcon(@RequestBody RequestGiftIcon requestGiftIcon) {
        return iGiftIconService.addGiftIcon(requestGiftIcon);
    }

    @GetMapping("/get/{id}")
    public ResponseGiftIcon getGiftIcon(@PathVariable Long id) {
        return iGiftIconService.getGiftIcon(id);
    }
}
