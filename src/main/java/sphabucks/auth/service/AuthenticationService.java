package sphabucks.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sphabucks.auth.vo.*;
import sphabucks.config.JwtService;
import sphabucks.email.RedisService;
import sphabucks.email.service.EmailService;
import sphabucks.email.vo.RequestEmail;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.users.model.Role;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;

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

        return HttpStatus.OK;
    }

    public AuthenticationResponse Login(AuthenticationRequest authenticationRequest) {
        if(redis.getEmailCertification(authenticationRequest.getLoginId())!=null) {
            if (redis.getEmailCertification(authenticationRequest.getLoginId()).substring(0, 6).equals("logout")) {
                throw new BusinessException(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getCode());
            }
        }
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
                .refreshToken(refreshToken)
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

    public boolean chkEmailWhenSignUp(RequestEmail requestEmail) throws Exception {
        if (userRepository.existsByEmail(requestEmail.getEmail())) {
            return false;
        } else {
            emailService.sendSimpleMessage(requestEmail.getEmail());
            return true;
        }
    }

    public boolean chkEmailWhenFindId(RequestFindId requestFindId) throws Exception {
        if (!userRepository.existsByEmailAndName(requestFindId.getEmail(), requestFindId.getUserName())) {
            return false;
        } else {
            emailService.sendSimpleMessage(requestFindId.getEmail());
            return true;
        }
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
    public void Logout(RequestToken requestToken){
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(requestToken.getAccessToken()));
        if(!jwtService.isTokenValid(requestToken.getAccessToken(), userDetails)){
            throw new BusinessException(ErrorCode.TOKEN_NOT_EXISTS, ErrorCode.TOKEN_NOT_EXISTS.getCode());
        }

        Authentication authentication = jwtService.getAuthentication(requestToken.getAccessToken());


        StringTokenizer st = new StringTokenizer(authentication.getCredentials().toString(),"=,");
        st.nextToken();
        String str = st.nextToken();

        if(redis.getEmailCertification(str) != null){
            redis.removeUserId(requestToken.getUserId());
        }
        redis.changeExpired(requestToken.getUserId(), requestToken.getAccessToken());

    }



















}
