package sphabucks.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sphabucks.domain.users.model.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/swagger-ui/**"
                        , "/swagger-ui.html"
                        , "/swagger-resources/**"
                        , "/v3/api-docs/**"
                ).permitAll()
                .requestMatchers(
                        "/api/v1/tag/**"
                        , "/api/v1/tag/**"
                        , "/api/v1/bigCategory/**"
                        , "/api/v1/product-category/**"
                        , "/api/v1/product/**"
                        , "/api/v1/smallCategory/**"
                        , "/api/v1/event/**"
                        , "/api/v1/event-products/**"
                        , "/api/v1/auth/**"
                ).permitAll()   // 설정한 리소스의 접근을 인증절차 없이 접근 가능
                .requestMatchers(
                        "/admin/**"
                        , "/api/v1/user/**"
                        , "/api/v1/card/**"
                        , "/api/v1/coupon/**"
                        , "/api/v1/gift-icon/**"
                ).hasRole(Role.ADMIN.toString())    // ADMIN 권한을 가지고 있어야 접근 가능
                .anyRequest().authenticated()   // 그 외 모든 리소스를 의미하며 인증 필요
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
