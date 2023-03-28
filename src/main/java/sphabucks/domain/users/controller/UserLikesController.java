package sphabucks.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.users.vo.RequestUserLikes;
import sphabucks.domain.users.service.IUserLikesService;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/user-likes")
@RequiredArgsConstructor
@Tag(name = "좋아요 기능")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class UserLikesController {

    private final IUserLikesService iUserLikesService;

    @PutMapping("/push")
    @Operation(summary = "좋아요 클릭", description = "다시 한번 눌리면 좋아요 취소")
    ResponseEntity<Object> pushUserLikes(@RequestBody RequestUserLikes requestUserLikes, @RequestHeader String userId){
        requestUserLikes.setUserId(userId);
        iUserLikesService.pushUserLikes(requestUserLikes);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get")
    @Operation(summary = "좋아요 클릭한 상품 조회")
    ResponseEntity<Object> getUserLikes(@RequestHeader String userId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iUserLikesService.getUserLikes(userId)));
    }

    @GetMapping("/get/all")
    @Operation(summary = "고객이 좋아요를 누른 모든 상품 조회", description = "어드민 권한 - 삭제 예정?")
    ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iUserLikesService.getAll()));
    }
}
