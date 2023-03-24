package sphabucks.email.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.email.service.EmailService;
import sphabucks.email.vo.RequestEmail;
import sphabucks.email.vo.RequestEmailCheck;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/email")
@Tag(name = "회원 인증", description = "회원가입, 로그인, 로그아웃 등")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @Operation(summary = "인증 메일 전송")
    public String sendEmail(@RequestBody RequestEmail requestEmail) throws Exception{

        return emailService.sendSimpleMessage(requestEmail.getEmail());
    }

    @PostMapping("/check")
    @Operation(summary = "인증 번호 확인")
    public Boolean checkEmailCode(@RequestBody RequestEmailCheck requestEmailCheck) throws Exception{

        return emailService.checkEmailCode(requestEmailCheck);
    }


}
