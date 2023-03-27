package sphabucks.domain.purchaseHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseHistoryList;
import sphabucks.domain.purchaseHistory.service.IPurchaseHistoryService;

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
    public ResponseEntity<Object> addPurchaseHistory(@RequestBody List<Long> selected, @RequestHeader String userId){

        iPurchaseHistoryService.addPurchaseHistory(selected, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get")
    @Operation(summary = "구매 내역 조회", description = "프론트와 테스트 후 수정 필요")
    public ResponseEntity<Object> getPurchaseHistory(@RequestHeader String userId) {

        return ResponseEntity.status(HttpStatus.OK).body(iPurchaseHistoryService.getPurchaseHistoryList(userId));
    }






//    @GetMapping("/test")
//    public List<String> test (@RequestHeader String userId) {
//        return iPurchaseHistoryService.getAllPaymentNum(userId);
//    }
//
//    @GetMapping("/test2")
//    public List<List<PurchaseHistory>> test2 (@RequestHeader String userId) {
//        List<String> list = iPurchaseHistoryService.getAllPaymentNum(userId);
//        List<List<PurchaseHistory>> responsePurchaseHistoryLists = new ArrayList<>();
//
//        list.forEach(item -> {
//            List<PurchaseHistory> purchaseHistoryList = iPurchaseHistoryService.getAllByPaymentNum(userId, item);
//            responsePurchaseHistoryLists.add(purchaseHistoryList);
//        });
//
//        return responsePurchaseHistoryLists;
//    }

}
