package sphabucks.payments.gifticons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.gifticons.model.GiftIconList;
import sphabucks.payments.gifticons.service.IGiftIconListService;
import sphabucks.payments.gifticons.vo.RequestGiftIconList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gifticon-list")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class GiftIconListController {
    private final IGiftIconListService iGiftIconListService;

    @PostMapping("/add")
    public void addGiftIconList(@RequestBody RequestGiftIconList requestGiftIconList) {
        iGiftIconListService.addGiftIconList(requestGiftIconList);
    }

    @GetMapping("/get/{id}")
    public List<GiftIconList> getGiftIconList(@PathVariable Long id) {
        return iGiftIconListService.getGiftIconList(id);
    }
}
