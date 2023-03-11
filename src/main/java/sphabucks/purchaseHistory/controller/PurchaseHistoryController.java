package sphabucks.purchaseHistory.controller;

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
public class PurchaseHistoryController {

    private final IPurchaseHistoryService iPurchaseHistoryService;

    @PostMapping("/add")
    public PurchaseHistory addPurchaseHistory(@RequestBody RequestPurchaseHistory requestPurchaseHistory){
        return iPurchaseHistoryService.addPurchaseHistory(requestPurchaseHistory);
    }

    @GetMapping("/get/{id}")
    public PurchaseHistory getPurchaseHistory(@PathVariable Long id) {
        return iPurchaseHistoryService.getPurchaseHistory(id);
    }

}
