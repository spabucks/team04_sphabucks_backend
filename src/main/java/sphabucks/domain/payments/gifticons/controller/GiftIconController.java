package sphabucks.domain.payments.gifticons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.gifticons.vo.RequestGiftIcon;
import sphabucks.domain.payments.gifticons.service.IGiftIconService;

@RestController
@RequestMapping("/api/v1/gift-icon")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class GiftIconController {
    private final IGiftIconService iGiftIconService;

    @PostMapping("/add")
    @Operation(summary = "기프티콘 정보 등록", description = "어드민 권한 - 삭제 예정?")
    public ResponseEntity<Object> addGiftIcon(@RequestBody RequestGiftIcon requestGiftIcon) {
        iGiftIconService.addGiftIcon(requestGiftIcon);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "기프티콘 정보 조회", description = "어드민 권한인데 고객이 사용할 가능성도 있음")
    public ResponseEntity<Object> getGiftIcon(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iGiftIconService.getGiftIcon(id));
    }
}
