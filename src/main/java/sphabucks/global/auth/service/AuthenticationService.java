package sphabucks.global.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sphabucks.global.config.JwtService;
import sphabucks.global.email.RedisService;
import sphabucks.global.email.service.EmailService;
import sphabucks.global.email.vo.RequestEmail;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.global.auth.vo.*;
import sphabucks.domain.users.model.Role;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.repository.IUserRepository;

import java.util.StringTokenizer;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RedisService redis;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final EmailService emailService;


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

        return HttpStatus.CREATED;
    }
    
    public AuthenticationResponse Login(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRepository.findByLoginId(authenticationRequest.getLoginId())
                                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                                .getUserId(),
                        authenticationRequest.getPwd()
                )
        );

        var user = userRepository.findByLoginId(authenticationRequest.getLoginId())
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(jwtToken);
        redis.createEmailByRefreshToken(user.getLoginId(), refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .nickName(user.getNickname() == null ? user.getLoginId() : user.getNickname() )
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

    public boolean chkEmailWhenSignUp(RequestEmail requestEmail) {
        return !userRepository.existsByEmail(requestEmail.getEmail());
    }

    public boolean chkEmailWhenFindId(RequestFindId requestFindId) {
        return userRepository.existsByEmailAndName(requestFindId.getEmail(), requestFindId.getUserName());
    }

    public String findId(RequestFindId requestFindId) {
        return userRepository.findByNameAndEmail(requestFindId.getUserName(), requestFindId.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()))
                .getLoginId().toUpperCase();
    }
    public String findPassword(RequestFindPassword requestFindPassword) {
        Boolean result = userRepository.existsByEmailAndLoginIdAndName(
                requestFindPassword.getEmail(),
                requestFindPassword.getLoginId(),
                requestFindPassword.getName());

        if (!result) {
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode());
        } else {
            try {
                return emailService.sendSimpleMessage(requestFindPassword.getEmail());
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getCode());
            }
        }
    }

    @Transactional
    public void resetPassword(RequestResetPassword requestResetPassword) {
        if (userRepository.findByLoginId(requestResetPassword.getLoginId()).isEmpty()) {
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode());
        } else {
            userRepository.resetPassword(
                    passwordEncoder.encode(requestResetPassword.getNewPassword()),
                    requestResetPassword.getLoginId()
            );
        }
    }

    @Transactional
    public void Logout(String userId, String access){

        StringTokenizer st = new StringTokenizer(access," ");
        st.nextToken();
        access = st.nextToken();
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(access));

        if(!jwtService.isTokenValid(access, userDetails)){
            throw new BusinessException(ErrorCode.TOKEN_NOT_EXISTS, ErrorCode.TOKEN_NOT_EXISTS.getCode());
        }

        if(redis.getEmailCertification(access) != null){
            redis.removeUserId(userId);
        }
        redis.changeExpired(access);

    }



















}
