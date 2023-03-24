package sphabucks.email;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@Slf4j
@Setter
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfig {

    private String host;
    private String password;
    private Integer port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }
    // 내 PC (Redis)에 비밀번호를 안걸면 실행된다 but , 비번을 걸고 비번세팅을 하면 되지 않는다.
    // 비밀번호 만들고, 저장하는 객체가 작동되지 않아서 ..
    // 사용하려면 denpendency를 reactiveredis로 설정하면 된다.

//    @Bean
//    @Primary
//    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisConfiguration defaultRedisConfig) {
//        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
//                .useSsl().build();
//        return new LettuceConnectionFactory(defaultRedisConfig, lettuceClientConfiguration);
//    }
//
//    @Bean
//    public RedisConfiguration defaultRedisConfig() {
//        log.info("redis login information : {} {} {} ", host, password, port);
//
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName(host);
//        config.setPassword(RedisPassword.of(password));
//        config.setPort(port);
//        return config;
//    }
}
