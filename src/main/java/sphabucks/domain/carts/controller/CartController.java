package sphabucks.domain.carts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.carts.service.ICartService;
import sphabucks.domain.carts.vo.*;
import sphabucks.global.responseEntity.ResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Tag(name = "장바구니(카트)")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Slf4j
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/add")
    @Operation(summary = "장바구니 담기")
    public ResponseEntity<Object> addCart(
            Authentication authentication, @RequestBody RequestCart requestCart){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        return iCartService.addCart(userId, requestCart);
    }

    @GetMapping("/get")
    @Operation(summary = "장바구니 조회", description = "uuid 사용")
    public ResponseEntity<Object> getCart(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();
        return ResponseEntity.status(HttpStatus.OK).body(iCartService.getCart(userId));
    }

    @GetMapping("/get/v2")
    @Operation(summary = "장바구니 조회 v2", description = "유저의 카트 속 모든 정보를 한번에 반환해줌")
    public ResponseEntity<Object> getCartV2(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iCartService.getCartV2(userId)));
    }

    @GetMapping("/get/product/{id}")
    public ResponseEntity<Object> getCartProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iCartService.getCartProduct(id)));
    }

    @PatchMapping("/update")
    @Operation(summary = "장바구니 수정")
    public ResponseEntity<Object> updateCart(@RequestBody RequestUpdateCart request){
        iCartService.updateCart(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/delete")
    @Operation(summary = "장바구니에서 선택 상품 삭제")
    public ResponseEntity<Object> deleteCart(@RequestBody RequestDeleteCart request){
        iCartService.deleteCart(request.getCartId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/selectedDelete")
    @Operation(summary = "장바구니에서 선택 상품 여러개 삭제")
    public ResponseEntity<Object> selectedDeleteCart(@RequestBody RequestDeleteSelectedCart request) {
        iCartService.deleteSelectedCart(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/delete/all")
    @Operation(summary = "장바구니 전체 삭제")
    public ResponseEntity<Object> deleteAll(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iCartService.deleteAll(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}