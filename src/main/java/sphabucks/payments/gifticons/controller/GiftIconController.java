package sphabucks.payments.gifticons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.gifticons.model.GiftIconList;
import sphabucks.payments.gifticons.service.IGiftIconListService;
import sphabucks.payments.gifticons.service.IGiftIconService;
import sphabucks.payments.gifticons.vo.RequestGiftIcon;
import sphabucks.payments.gifticons.vo.RequestGiftIconList;
import sphabucks.payments.gifticons.vo.ResponseGiftIcon;

import java.util.List;

@RestController
@RequestMapping("/gift-icons")
@RequiredArgsConstructor
public class GiftIconController {
    private final IGiftIconService iGiftIconService;
    private final IGiftIconListService iGiftIconListService;

    @PostMapping("/add")
    public void addGiftIcon(@RequestBody RequestGiftIcon requestGiftIcon) {
        iGiftIconService.addGiftIcon(requestGiftIcon);
    }

    @GetMapping("/get/{id}")
    public ResponseGiftIcon getGiftIcon(@PathVariable Long id) {
        return iGiftIconService.getGiftIcon(id);
    }
}
