package sphabucks.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sphabucks.auth.vo.AuthenticationRequest;
import sphabucks.auth.vo.AuthenticationResponse;
import sphabucks.auth.vo.RefreshRequest;
import sphabucks.auth.vo.RequestSignUp;
import sphabucks.config.JwtService;
import sphabucks.email.RedisService;
import sphabucks.email.RequestEmail;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.users.model.Role;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.vo.RequestUser;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RedisService redis;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public HttpStatus signup(RequestSignUp requestSignUp) {

        var user = User.builder()
                .loginId(requestSignUp.getLoginId())
                .userId(UUID.randomUUID().toString())
                .birth(requestSignUp.getBirth())
                .name(requestSignUp.getUserName())
                .nickname(requestSignUp.getNickName())
                .phoneNum(requestSignUp.getPhoneNum())
                .pwd(passwordEncoder.encode(requestSignUp.getPwd()))
                .email(requestSignUp.getEmail())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return HttpStatus.OK;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLoginId(),
                        authenticationRequest.getPwd()
                )
        );
        var user = userRepository.findByLoginId(authenticationRequest.getLoginId())
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(jwtToken);
        redis.createEmailByRefreshToken(user.getLoginId(), refreshToken);

        return AuthenticationResponse.builder()
                .userId(userRepository.findByLoginId(authenticationRequest.getLoginId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                        .getUserId())
                .accessToken(jwtToken)
                .build();
    }


    public AuthenticationResponse refresh(RefreshRequest refreshRequest){
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(refreshRequest.getRefreshToken()));
        var redisUserLoginId = redis.getEmailByRefreshToken(refreshRequest.getRefreshToken());
        if(!userDetails.getUsername().equals(redisUserLoginId) &&
                !jwtService.isTokenValid(refreshRequest.getRefreshToken(), userDetails)){
            throw new RuntimeException("Refresh token is not valid");
        }
        var jwtAToken = jwtService.generateToken(userDetails);
        var jwtRToken = jwtService.refreshToken(jwtAToken);


        return AuthenticationResponse.builder()
                .accessToken(jwtAToken)
                .refreshToken(jwtRToken)
                .build();
    }

// 지욱
    public Boolean chkEmailIsDuplicate(RequestEmail requestEmail) {
        return userRepository.existsByEmail(requestEmail.getEmail());
    }
// 지욱





















}
