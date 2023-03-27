package sphabucks.domain.carts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addCart(@RequestBody RequestCart requestCart){
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.addCart(requestCart)));
    }

    @GetMapping("/get/{userId}")
    @Operation(summary = "장바구니 조회", description = "uuid 사용")
    public ResponseEntity<Object> getCart(@PathVariable String userId){
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.getCart(userId)));
    }

    @GetMapping("/get/v2/{userId}")
    @Operation(summary = "장바구니 조회 v2", description = "유저의 카트 속 모든 정보를 한번에 반환해줌")
    public ResponseEntity<Object> getCartV2(@PathVariable String userId) {
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.getCartV2(userId)));
    }

    @GetMapping("/get/product/{id}")
    public ResponseEntity<Object> getCartProduct(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.getCartProduct(id)));
    }

    @PatchMapping("/update")
    @Operation(summary = "장바구니 수정")
    public ResponseEntity<Object> updateCart(@RequestBody RequestUpdateCart request){
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.updateCart(request)));
    }

    @PatchMapping("/delete")
    @Operation(summary = "장바구니에서 선택 상품 삭제")
    public ResponseEntity<Object> deleteCart(@RequestBody RequestDeleteCart request){
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.deleteCart(request.getCartId())));
    }

    @PutMapping("/selectedDelete")
    @Operation(summary = "장바구니에서 선택 상품 여러개 삭제")
    public ResponseEntity<Object> selectedDeleteCart(@RequestBody List<RequestDeleteSelectedCart> request) {
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.deleteSelectedCart(request)));
    }

    @PutMapping("/delete/all")
    @Operation(summary = "장바구니 전체 삭제")
    public ResponseEntity<Object> deleteAll(@RequestBody RequestDeleteAll request){
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, iCartService.deleteAll(request.getUserId())));
    }
}