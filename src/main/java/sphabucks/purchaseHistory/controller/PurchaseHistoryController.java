package sphabucks.purchaseHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.service.IPurchaseHistoryService;
import sphabucks.purchaseHistory.vo.RequestPurchaseHistory;

@RestController
@RequestMapping("/api/v1/purchaseHistory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Slf4j
@Tag(name = "구매 내역")
public class PurchaseHistoryController {

    private final IPurchaseHistoryService iPurchaseHistoryService;

    @PostMapping("/add")
    @Operation(summary = "구매 내역 추가", description = "구현 X")
    public PurchaseHistory addPurchaseHistory(@RequestBody RequestPurchaseHistory requestPurchaseHistory){
        return iPurchaseHistoryService.addPurchaseHistory(requestPurchaseHistory);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "구매 내역 조회", description = "구현 X")
    public PurchaseHistory getPurchaseHistory(@PathVariable Long id) {
        return iPurchaseHistoryService.getPurchaseHistory(id);
    }

}
