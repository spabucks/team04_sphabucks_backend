package sphabucks.global.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import sphabucks.global.config.JwtService;

import java.time.Duration;

@Repository
@Slf4j
public class RedisService {

    private final int LIMIT_TIME = 5 * 60;
    private final int ALIMIT_TIME = 60 * 60;
    private final int RLIMIT_TIME = 70 * 60;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private JwtService jwtService;
    // 앞에는 이메일, 뒤에는 생성한 키값

    public void createEmailCertification(String email, String certificationNumber) {
        // redisTemplate.opsForValue().set(email, certificationNumber);
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(email, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
    }

    public String getEmailCertification(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public String getEmailByRefreshToken(String refreshToken){
        return redisTemplate.opsForValue().get(refreshToken);
    }
    public void createEmailByRefreshToken(String email, String refreshToken){
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(email, refreshToken, Duration.ofSeconds(RLIMIT_TIME));
    }
    public void removeEmailCertification(String email) {
        redisTemplate.delete(email);
    }
    public void removeUserId(String userId){
        redisTemplate.delete(userId);
    }


    public boolean hasKey(String email) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(email));
    }

    public void changeExpired(String accessToken){

        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(accessToken, "logout", Duration.ofSeconds(ALIMIT_TIME));

    }

}
