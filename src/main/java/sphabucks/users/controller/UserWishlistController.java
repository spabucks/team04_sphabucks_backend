package sphabucks.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.repository.IProductRepository;
import sphabucks.users.model.UserWishlist;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.repository.IUserWishlistRepo;
import sphabucks.users.service.IUserWishlistService;
import sphabucks.users.vo.RequestUserWishlist;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userwishlist")
@RequiredArgsConstructor
@Tag(name = "위시리스트", description = "온라인 스토어에는 없는기능 > 직접 구현해야함")
public class UserWishlistController {

    private final IUserWishlistService iUserWishlistService;

    @PostMapping("/add")
    @Operation(summary = "위시리스트에 상품 담기", description = "구현 X")
    void addUserWishlist(@RequestBody RequestUserWishlist requestUserWishlist){
        iUserWishlistService.addUserWishlist(requestUserWishlist);
    }

    @GetMapping("/get/{userId}")
    @Operation(summary = "위시리스트에 담은 상품 확인", description = "구현 X")
    List<UserWishlist> getUserWishlist(@PathVariable Long userId){
        return iUserWishlistService.getByUserWishlist(userId);
    }

}
