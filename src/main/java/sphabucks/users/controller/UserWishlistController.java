package sphabucks.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.UserWishlist;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.repository.IUserWishlistRepo;
import sphabucks.users.service.IUserWishlistService;
import sphabucks.users.vo.RequestUserWishlist;
import sphabucks.users.vo.ResponseWishList;
import sphabucks.users.vo.ResponseWishListProduct;

import java.util.List;

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
    void clickUserWishlist(@RequestBody RequestUserWishlist requestUserWishlist){
        log.info("uuid : " + requestUserWishlist.getUserId());
        log.info("productId : " + requestUserWishlist.getProductId().toString());
        iUserWishlistService.clickWishList(requestUserWishlist);
    }

    @GetMapping("/get/{userId}")
    @Operation(summary = "위시리스트에 담은 상품 확인", description = "구현 중")
    List<ResponseWishList> getUserWishlist(@PathVariable String userId){
        return iUserWishlistService.getByUserWishlist(userId);
    }

    @GetMapping("/get/product/{id}")
    @Operation(summary = "위시리스트 상품 정보 받아오기", description = "/api/v1/userwishlist/get/{userId} 를 통해 받아온 리스트 속 productId 사용")
    ResponseWishListProduct getWishListProduct(@PathVariable Long id) {
        return iUserWishlistService.getWishListProduct(id);
    }
}
