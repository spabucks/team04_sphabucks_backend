package sphabucks.global.email.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.global.email.service.EmailService;
import sphabucks.global.email.vo.RequestEmail;
import sphabucks.global.email.vo.RequestEmailCheck;
import sphabucks.global.responseEntity.ResponseDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/email")
@Tag(name = "회원 인증", description = "회원가입, 로그인, 로그아웃 등")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @Operation(summary = "인증 메일 전송")
    public ResponseEntity<Object> sendEmail(@RequestBody RequestEmail requestEmail) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                HttpStatus.OK,
                emailService.sendSimpleMessage(requestEmail.getEmail())
        ));
    }

    @PostMapping("/check")
    @Operation(summary = "인증 번호 확인")
    public ResponseEntity<Object> checkEmailCode(@RequestBody RequestEmailCheck requestEmailCheck) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                HttpStatus.OK,
                emailService.checkEmailCode(requestEmailCheck)
        ));
    }


}
