package sphabucks.payments.gifticons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.gifticons.model.GiftIconList;
import sphabucks.payments.gifticons.service.IGiftIconListService;
import sphabucks.payments.gifticons.vo.RequestGiftIconList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gifticon-list")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
public class GiftIconListController {
    private final IGiftIconListService iGiftIconListService;

    @PostMapping("/add")
    @Operation(summary = "고객이 자신의 계정에 기프티콘 등록", description = "구현 X")
    public void addGiftIconList(@RequestBody RequestGiftIconList requestGiftIconList) {
        iGiftIconListService.addGiftIconList(requestGiftIconList);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "고객이 자신이 가지고 있는 모든 기프티콘 확인", description = "구현 X")
    public List<GiftIconList> getGiftIconList(@PathVariable Long id) {
        return iGiftIconListService.getGiftIconList(id);
    }
}
