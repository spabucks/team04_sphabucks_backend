package sphabucks.payments.gifticons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sphabucks.payments.gifticons.model.GiftIconList;
import sphabucks.payments.gifticons.service.IGiftIconListService;
import sphabucks.payments.gifticons.vo.RequestGiftIconList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gifticon-list")
@RequiredArgsConstructor
public class GiftIconListController {
    private final IGiftIconListService iGiftIconListService;

    @PostMapping
    public void addGiftIconList(RequestGiftIconList requestGiftIconList) {
        iGiftIconListService.addGiftIconList(requestGiftIconList);
    }

    @GetMapping
    public List<GiftIconList> getGiftIconList(Long id) {
        return iGiftIconListService.getGiftIconList(id);
    }
}
