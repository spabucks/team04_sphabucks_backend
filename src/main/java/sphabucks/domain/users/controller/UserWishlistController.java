package sphabucks.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.users.service.IUserWishlistService;
import sphabucks.domain.users.vo.RequestUserWishlist;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/userwishlist")
@RequiredArgsConstructor
@Tag(name = "위시리스트", description = "온라인 스토어에는 없는기능 > 직접 구현해야함")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Slf4j
public class UserWishlistController {

    private final IUserWishlistService iUserWishlistService;

    @PostMapping()
    @Operation(summary = "위시리스트에 상품 담기", description = "구현 완료")
    ResponseEntity<Object> clickUserWishlist(
            Authentication authentication,
            @RequestBody RequestUserWishlist requestUserWishlist) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iUserWishlistService.clickWishList(userId, requestUserWishlist);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get")
    @Operation(summary = "위시리스트에 담은 상품 확인", description = "구현 중")
    ResponseEntity<Object> getUserWishlist(Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, iUserWishlistService.getByUserWishlist(userId)));
    }

    @GetMapping("/get/product/{id}")
    @Operation(summary = "위시리스트 상품 정보 받아오기", description = "/api/v1/userwishlist/get/{userId} 를 통해 받아온 리스트 속 productId 사용")
    ResponseEntity<Object> getWishListProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iUserWishlistService.getWishListProduct(id)));
    }
}
