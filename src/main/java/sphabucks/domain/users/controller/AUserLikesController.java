package sphabucks.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sphabucks.domain.users.service.IUserLikesService;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/admin/api/v1/user-likes")
@RequiredArgsConstructor
@Tag(name = "UserLikesAdmin")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class AUserLikesController {

    private final IUserLikesService iUserLikesService;

    @GetMapping("/get/all")
    @Operation(summary = "고객이 좋아요를 누른 모든 상품 조회", description = "어드민 권한 - 삭제 예정?")
    ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iUserLikesService.getAll()));
    }
}
