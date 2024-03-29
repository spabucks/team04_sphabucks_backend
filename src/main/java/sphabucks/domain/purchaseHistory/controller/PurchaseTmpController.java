package sphabucks.domain.purchaseHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.carts.service.ICartService;
import sphabucks.domain.carts.vo.RequestCart;
import sphabucks.domain.purchaseHistory.model.PurchaseTmp;
import sphabucks.domain.purchaseHistory.service.IPurchaseTmpService;
import sphabucks.domain.purchaseHistory.vo.RequestPurchaseTmp;
import sphabucks.domain.purchaseHistory.vo.ResponsePurchaseTmp;
import sphabucks.global.responseEntity.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchaseTmp")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name="구매 내역")
@Slf4j
public class PurchaseTmpController {

    private final IPurchaseTmpService iPurchaseTmpService;
    private final ICartService iCartService;

    @PostMapping("/add")
    @Operation(summary = "임시 저장용 구매 테이블에 값 추가", description = "결제하는 상품 담아두는 임시 테이블에 값 추가")
    public ResponseEntity<Object> addPurchaseTmp(Authentication authentication, @RequestBody RequestPurchaseTmp requestPurchaseTmp) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iPurchaseTmpService.addPurchaseTmp(userId, requestPurchaseTmp);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/get")
    @Operation(summary = "임시 저장용 구매 테이블 조회", description = "결제하는 상품 담아두는 임시 테이블 조회")
    public ResponseEntity<Object> getPurchaseTmp(Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String userId = userDetails.getUsername();

        List<ResponsePurchaseTmp> list = iPurchaseTmpService.getPurchaseTmp(userId);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, list));
    }

    @PostMapping("/addOne")
    @Operation(summary = "상품 단건 구매", description = "상품 단건 구매, 구매하기 버튼 클릭시 tmp 테이블에 저장")
    public ResponseEntity<Object> orderOneProduct(
            Authentication authentication, @RequestBody RequestCart requestCart) {

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String userId = userDetails.getUsername();

        String cartId = iCartService.addCartFromPurchase(userId, requestCart).getBody().toString();

        iPurchaseTmpService.addPurchaseTmpOne(userId, Long.parseLong(cartId));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
