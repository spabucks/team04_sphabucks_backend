package sphabucks.global.email.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sphabucks.global.email.RedisService;
import sphabucks.global.email.vo.RequestEmailCheck;
import sphabucks.domain.users.repository.IUserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    public static final String ePw = createKey();
    private final RedisService redisService;
    private final IUserRepository iUserRepository;

    // 전송 메세지 생성
    private MimeMessage createMessage(String to, String code) throws Exception{

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, to); // 받는 사람
        message.setSubject("Sphabucks 메일 인증"); // 제목

        String msg = "";
        msg += "<img width=\"300\" height=\"300\" style=\"margin-top: 0; margin-right: 0; margin-bottom: 0px; margin-left: 0px; padding-right: 30px; padding-left: 30px;\" src=\"https://postfiles.pstatic.net/20160604_276/ppanppane_1465006344233mrvD4_PNG/%B7%CE%B0%ED-23.png?type=w966\" alt=\"\" loading=\"lazy\">";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px; margin-top: 0;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 Sphabucks 가입 창이 있는 브라우저 창에 입력하세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px; letter-spacing: 30px; padding-left: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";
        msg += "<a href=\"#\" style=\"text-decoration: none; color: #434245;\" rel=\"noreferrer noopener\" target=\"_blank\">Shpabucks Clone Technologies, Inc</a>";

        message.setText(msg, "utf-8", "html"); // 내용
        message.setFrom(new InternetAddress("drodudals1@gmail.com", "Sphabucks")); // 보내는 사람

        return message;
    }

    // 6자리 난수 생성
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    // 인증 코드 만들기
    public String createCode(String ePw) {
        return ePw.substring(0, 3) + ePw.substring(3, 6);
    }


    // 메세지 전송
    public String sendSimpleMessage(String to) throws Exception{

        String code = createCode(ePw);
        MimeMessage message= createMessage(to, code);
        redisService.createEmailCertification(to, ePw);

        try {
            javaMailSender.send(message);
            return "이메일 전송";
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    // 이메일 인증 코드 체크
    public Boolean checkEmailCode(RequestEmailCheck requestEmailCheck){

        if (redisService.hasKey(requestEmailCheck.getEmail())) {
            String redisCode = redisService.getEmailCertification(requestEmailCheck.getEmail());
            if (redisCode.equals(requestEmailCheck.getCode())) {
                redisService.removeEmailCertification(requestEmailCheck.getEmail());

                return true; // 인증 성공
            }
        }
        return false; // 인증 실패
    }
}
