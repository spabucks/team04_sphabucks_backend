package sphabucks.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.auth.vo.*;
import sphabucks.auth.service.AuthenticationService;

import sphabucks.email.vo.RequestEmail;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
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
    @Operation(summary = "로그인", description = "로그인 시 access 토큰, uuid, userid(추가예정) 반환")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            @RequestBody RefreshRequest refreshRequest){
        return ResponseEntity.ok(authenticationService.refresh(refreshRequest));
    }

    // 영민 (비밀번호 찾기)
    @PostMapping("/findPassword")
    @Operation(summary = "비밀번호 찾기", description = "이름, loginId, email이 일치하는 회원이 있으면 메일 전송")
    public String findPassword(@RequestBody RequestFindPassword requestFindPassword) {
        return authenticationService.findPassword(requestFindPassword);
    }

    @PostMapping("/resetPassword")
    @Operation(summary = "비밀번호 재설정", description = "비밀번호 재설정, 비밀번호 변경할 유저 loginId도 함께 줘야 함")
    public void resetPassword(@RequestBody RequestResetPassword requestResetPassword) {

        log.info("@@@@@@@@@@@@@@@@@@{}", requestResetPassword);

        authenticationService.resetPassword(requestResetPassword);
    }

    // 영민



    @PostMapping("/signup/chkemail")
    @Operation(summary = "회원가입 시 이메일 중복 체크(유무 체크)", description = "회원가입 시 중복일 경우 진행 X")
    public Boolean chkEmailWhenSignUp(@RequestBody RequestEmail requestEmail) throws Exception {
        return authenticationService.chkEmailWhenSignUp(requestEmail);
    }

    @PostMapping("/findid/chkemail")
    @Operation(summary = "아이디 찾기 시 이메일 중복 체크(유무 체크)", description = "중복이 아닐 경우 진행 X(이메일이 DB에 있어야함)")
    public Boolean chkEmailWhenFindId(@RequestBody RequestFindId requestFindId) throws Exception {
        return authenticationService.chkEmailWhenFindId(requestFindId);
    }


    @PostMapping("/findid")
    @Operation(summary = "아이디 찾기", description = "이메일 인증 후 해당 이메일과 실명으로 아이디를 찾아서 반환")
    public String findId(@RequestBody RequestFindId requestFindId) {
        return authenticationService.findId(requestFindId);
    }
}
