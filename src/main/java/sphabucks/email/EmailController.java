package sphabucks.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam("email") String email) throws Exception{
        emailService.sendSimpleMessage(email);

        return "전송 완료";
    }

}
