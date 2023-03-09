package sphabucks.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisService {

    private final int LIMIT_TIME = 5 * 60;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    // 앞에는 이메일, 뒤에는 생성한 키값

    public void createEmailCertification(String email, String certificationNumber) {
        // redisTemplate.opsForValue().set(email, certificationNumber);
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(email, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
    }

    public String getEmailCertification(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public void removeEmailCertification(String email) {
        redisTemplate.delete(email);
    }

    public boolean hasKey(String email) {
        return redisTemplate.hasKey(email);
    }

}
