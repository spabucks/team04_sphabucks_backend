package sphabucks.purchaseHistory.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.purchaseHistory.model.PurchaseHistory;
import sphabucks.purchaseHistory.service.IPurchaseHistoryService;

@RestController
@RequestMapping("/purchaseHistory")
@RequiredArgsConstructor
@Slf4j
public class PurchaseHistoryController {

    private final IPurchaseHistoryService iPurchaseHistoryService;

    @PostMapping("/add")
    public PurchaseHistory addPurchaseHistory(@RequestBody PurchaseHistory purchaseHistory){
        return iPurchaseHistoryService.addPurchaseHistory(purchaseHistory);
    }

    @GetMapping("/get/{id}")
    public PurchaseHistory getPurchaseHistory(@PathVariable Long id) {
        return iPurchaseHistoryService.getPurchaseHistory(id);
    }

}
