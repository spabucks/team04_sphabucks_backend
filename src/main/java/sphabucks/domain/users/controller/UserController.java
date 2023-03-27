package sphabucks.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.users.vo.RequestLoginIdCheck;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.service.IUserService;
import sphabucks.domain.users.vo.RequestUser;
import sphabucks.domain.users.vo.ResponseUser;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "고객 정보")
public class UserController {
    private final IUserService iUserService;

    @PostMapping("/add")
    @Operation(summary = "고객 정보 추가", description = "회원가입이 있으므로 마이페이지에서 정보수정 등으로 교체 예정")
    public void addUser(@RequestBody RequestUser requestUser) {

        iUserService.adduser(requestUser);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "고객 정보 조회", description = "필요 없어 보임")
    public ResponseUser getUser(@PathVariable Long id) {
        return iUserService.getUser(id);
    }

    @GetMapping("/get/email/{email}")
    @Operation(summary = "이메일로 고객 찾기", description = "필요 없어 보임")
    public ResponseUser getUserByEmail(@PathVariable String email) {
        return iUserService.getUserByEmail(email);
    }

    @GetMapping("/get/loginId/{loginId}")
    @Operation(summary = "로그인 아이디로 고객 찾기", description = "필요 없어 보임")
    public ResponseUser getUserByLoginId(@PathVariable String loginId) {
        return iUserService.getUserByLoginId(loginId);
    }

    @GetMapping("get/all")
    @Operation(summary = "모든 고객 정보 조회", description = "어드민 권한 - 삭제 예정")
    public List<User> getAll(){
        return iUserService.getAll();
    }

    @PostMapping("/check/loginId")
    @Operation(summary = "아이디 중복 체크", description = "회원가입시 아이디 중복체크")
    public Boolean checkLoginId(@RequestBody RequestLoginIdCheck requestLoginIdCheck) {
        return iUserService.existByLoginId(requestLoginIdCheck);
    }

}
