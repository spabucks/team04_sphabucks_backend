package sphabucks.users.controller;

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
@RequestMapping("/v1/api/userwishlist")
@RequiredArgsConstructor
public class UserWishlistController {

    private final IUserWishlistService iUserWishlistService;

    @PostMapping("/add")
    void addUserWishlist(@RequestBody RequestUserWishlist requestUserWishlist){
        iUserWishlistService.addUserWishlist(requestUserWishlist);
    }

    @GetMapping("/get/{userId}")
    List<UserWishlist> getUserWishlist(@PathVariable Long userId){
        return iUserWishlistService.getByUserWishlist(userId);
    }

}
