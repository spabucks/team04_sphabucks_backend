package sphabucks.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.auth.vo.*;
import sphabucks.auth.service.AuthenticationService;
import sphabucks.email.vo.RequestEmail;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "회원 인증", description = "회원가입, 로그인, 로그아웃 등")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "이메일 인증 후 진행되는 api")
    public ResponseEntity<HttpStatus> signup(
            @RequestBody RequestSignUp requestSignUp) {
        return ResponseEntity.ok(authenticationService.signup(requestSignUp));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            @RequestBody RefreshRequest refreshRequest){
        return ResponseEntity.ok(authenticationService.refresh(refreshRequest));
    }

    @PostMapping("/signup/chkemail")
    @Operation(summary = "회원가입 시 이메일 중복 체크(유무 체크)", description = "회원가입 시 중복일 경우 진행 X")
    public Boolean chkEmailIsDuplicate(@RequestBody RequestEmail requestEmail) throws Exception {
        return authenticationService.chkEmailWhenSignUp(requestEmail);
    }

    @PostMapping("/findid/chkemail")
    @Operation(summary = "아이디 찾기 시 이메일 중복 체크(유무 체크)", description = "중복이 아닐 경우 진행 X(이메일이 DB에 있어야함)")
    public Boolean chkEmailIsDuplicate(@RequestBody RequestFindId requestFindId) throws Exception {
        return authenticationService.chkEmailWhenFindId(requestFindId);
    }

    @PostMapping("/findid")
    @Operation(summary = "아이디 찾기", description = "이메일 인증 후 해당 이메일과 실명으로 아이디를 찾아서 반환")
    public String findId(@RequestBody RequestFindId requestFindId) {
        return authenticationService.findId(requestFindId);
    }
}
