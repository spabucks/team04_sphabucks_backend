package sphabucks.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "좋아요 기능")
public class UserLikesController {

    private final IUserLikesService iUserLikesService;

    @PostMapping("/add")
    @Operation(summary = "좋아요 클릭", description = "다시 한번 눌렀을 때는 구현 X")
    void addUserLikes(@RequestBody RequestUserLikes requestUserLikes){
        iUserLikesService.addUserLikes(requestUserLikes);
    }

    @GetMapping("/get/{userId}")
    @Operation(summary = "좋아요 클릭한 상품 조회?", description = "구현 X")
    List<UserLikes> getUserLikes(@PathVariable Long userId){
        return iUserLikesService.getUserLikes(userId);
    }

    @GetMapping("/get/all")
    @Operation(summary = "모든 고객이 좋아요를 누른 상품 조회", description = "어드민 권한 - 삭제 예정?")
    List<UserLikes> getAll(){
        return iUserLikesService.getAll();
    }
}
