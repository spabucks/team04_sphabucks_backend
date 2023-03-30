package sphabucks.global.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.users.service.IUserService;
import sphabucks.domain.users.vo.RequestLoginIdCheck;
import sphabucks.global.auth.service.AuthenticationService;
import sphabucks.global.email.vo.RequestEmail;
import sphabucks.global.auth.vo.*;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "회원 인증", description = "회원가입, 로그인, 로그아웃 등")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final IUserService iUserService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "이메일 인증 후 진행되는 api")
    public ResponseEntity<HttpStatus> signup(
            @RequestBody RequestSignUp requestSignUp) {
        return ResponseEntity.status(authenticationService.signup(requestSignUp)).build();

    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 시 access 토큰, uuid, userid(추가예정) 반환")
    public ResponseEntity<Object> login(
            @RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                HttpStatus.OK,
                authenticationService.Login(authenticationRequest)
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(
            @RequestBody RefreshRequest refreshRequest){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                HttpStatus.OK,
                authenticationService.refresh(refreshRequest)
        ));
    }

    @PostMapping("/findPassword")
    @Operation(summary = "비밀번호 찾기", description = "이름, loginId, email이 일치하는 회원이 있으면 메일 전송")
    public ResponseEntity<Object> findPassword(@RequestBody RequestFindPassword requestFindPassword) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, authenticationService.findPassword(requestFindPassword)));
    }

    @PutMapping("/resetPassword")
    @Operation(summary = "비밀번호 재설정", description = "비밀번호 재설정, 비밀번호 변경할 유저 loginId도 함께 줘야 함")
    public ResponseEntity<HttpStatus> resetPassword(@RequestBody RequestResetPassword requestResetPassword) {

        authenticationService.resetPassword(requestResetPassword);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(@RequestBody RequestToken requestToken){
        authenticationService.Logout(requestToken);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/signup/chkemail")
    @Operation(summary = "회원가입 시 이메일 중복 체크(유무 체크)", description = "회원가입 시 중복일 경우 진행 X")
    public ResponseEntity<Object> chkEmailWhenSignUp(@RequestBody RequestEmail requestEmail) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, authenticationService.chkEmailWhenSignUp(requestEmail)));
    }

    @PostMapping("/findid/chkemail")
    @Operation(summary = "아이디 찾기 시 이메일 중복 체크(유무 체크)", description = "중복이 아닐 경우 진행 X(이메일이 DB에 있어야함)")
    public ResponseEntity<Object> chkEmailWhenFindId(@RequestBody RequestFindId requestFindId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, authenticationService.chkEmailWhenFindId(requestFindId)));
    }


    @PostMapping("/findid")
    @Operation(summary = "아이디 찾기", description = "이메일 인증 후 해당 이메일과 실명으로 아이디를 찾아서 반환")
    public ResponseEntity<Object> findId(@RequestBody RequestFindId requestFindId) {

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, authenticationService.findId(requestFindId)));
    }

    @PostMapping("/check/loginId")
    @Operation(summary = "아이디 중복 체크", description = "회원가입시 아이디 중복체크")
    public ResponseEntity<Object> checkLoginId(@RequestBody RequestLoginIdCheck requestLoginIdCheck) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iUserService.existByLoginId(requestLoginIdCheck)));
    }


}
