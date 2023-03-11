package sphabucks.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.users.vo.RequestUser;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "회원 인증", description = "회원가입, 로그인, 로그아웃 등")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "필수 정보 : loginId, name, email, pwd")
    @Parameters(value = {
            @Parameter(name = "loginId", required = true, description = "로그인할 때 사용되는 id"),
            @Parameter(name = "name", required = true, description = "사용자 이름"),
            @Parameter(name = "email", required = true, description = "사용자 이메일"),
            @Parameter(name = "pwd", required = true, description = "로그인 비밀번호")
    })
    public ResponseEntity<AuthenticationResponse> signup(
            @RequestParam()
            @RequestBody RequestUser signupRequest) {
        return ResponseEntity.ok(authenticationService.signup(signupRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
