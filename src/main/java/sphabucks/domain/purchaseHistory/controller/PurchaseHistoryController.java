package sphabucks.domain.purchaseHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.purchaseHistory.service.IPurchaseHistoryService;
import sphabucks.global.responseEntity.ResponseDTO;

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
    public ResponseEntity<Object> addPurchaseHistory(Authentication authentication, @RequestBody List<Long> cartId){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iPurchaseHistoryService.addPurchaseHistory(cartId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get")
    @Operation(summary = "구매 내역 조회", description = "프론트와 테스트 후 수정 필요")
    public ResponseEntity<Object> getPurchaseHistory(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, iPurchaseHistoryService.getPurchaseHistoryList(userId)));
    }

}
