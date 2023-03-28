package sphabucks.domain.payments.gifticons.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.gifticons.service.IGiftIconListService;
import sphabucks.domain.payments.gifticons.vo.RequestGiftIconList;

@RestController
@RequestMapping("/api/v1/gifticon-list")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class GiftIconListController {
    private final IGiftIconListService iGiftIconListService;

    @PostMapping("/add")
    @Operation(summary = "고객이 자신의 계정에 기프티콘 등록", description = "구현 X")
    public ResponseEntity<Object> addGiftIconList(@RequestBody RequestGiftIconList requestGiftIconList) {
        iGiftIconListService.addGiftIconList(requestGiftIconList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "고객이 자신이 가지고 있는 모든 기프티콘 확인", description = "구현 X")
    public ResponseEntity<Object> getGiftIconList(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iGiftIconListService.getGiftIconList(id));
    }
}
