package sphabucks.payments.gifticons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/gift-icon")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class GiftIconController {
    private final IGiftIconService iGiftIconService;
    private final IGiftIconListService iGiftIconListService;

    @PostMapping("/add")
    @Operation(summary = "기프티콘 정보 등록", description = "어드민 권한 - 삭제 예정?")
    public void addGiftIcon(@RequestBody RequestGiftIcon requestGiftIcon) {
        iGiftIconService.addGiftIcon(requestGiftIcon);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "기프티콘 정보 조회", description = "어드민 권한인데 고객이 사용할 가능성도 있음")
    public ResponseGiftIcon getGiftIcon(@PathVariable Long id) {
        return iGiftIconService.getGiftIcon(id);
    }
}
