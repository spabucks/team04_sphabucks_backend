package sphabucks.purchaseHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.service.IPurchaseHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchaseHistory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Slf4j
@Tag(name = "구매 내역")
public class PurchaseHistoryController {

    private final IPurchaseHistoryService iPurchaseHistoryService;

    @PostMapping("/add")
    @Operation(summary = "구매 내역 추가", description = "폼태그로 데이터 어떻게 넘어오는지 확인 후 수정 필요할 듯")
    public void addPurchaseHistory(@RequestBody List<Long> selected, @RequestHeader String userId){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", selected);

        iPurchaseHistoryService.addPurchaseHistory(selected, userId);
    }

    @GetMapping("/get")
    @Operation(summary = "구매 내역 조회", description = "구현 X")
    public PurchaseHistory getPurchaseHistory(@RequestHeader Long userId) {
        return iPurchaseHistoryService.getPurchaseHistory(userId);
    }

}
