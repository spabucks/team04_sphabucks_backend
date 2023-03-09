package sphabucks.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;
    private final RedisService redisService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody RequestEmail requestEmail) throws Exception{

        return emailService.sendSimpleMessage(requestEmail.getEmail());
    }

    @PostMapping("/check")
    public Boolean checkEmailCode(@RequestBody RequestEmailCheck requestEmailCheck) throws Exception{

        return emailService.checkEmailCode(requestEmailCheck);
    }


}
