package sphabucks.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.users.model.UserLikes;
import sphabucks.users.repository.IUserLikesRepo;
import sphabucks.users.service.IUserLikesService;
import sphabucks.users.vo.RequestUserLikes;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-likes")
@RequiredArgsConstructor
public class UserLikesController {

    private final IUserLikesService iUserLikesService;

    @PostMapping("/add")
    void addUserLikes(@RequestBody RequestUserLikes requestUserLikes){
        iUserLikesService.addUserLikes(requestUserLikes);
    }

    @GetMapping("/get/{userId}")
    List<UserLikes> getUserLikes(@PathVariable Long userId){
        return iUserLikesService.getUserLikes(userId);
    }

    @GetMapping("/get/all")
    List<UserLikes> getAll(){
        return iUserLikesService.getAll();
    }
}
