package sphabucks.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sphabucks.config.JwtService;
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
        private final AuthenticationManager authenticationManager;
        public AuthenticationResponse signup(RequestUser signupRequest) {

            var user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .address(signupRequest.getAddress())
                    .birth(signupRequest.getBirth())
                    .grade(signupRequest.getGrade())
                    .sex(signupRequest.isSex())
                    .carNum(signupRequest.getCarNum())
                    .nickname(signupRequest.getNickname())
                    .phoneNum(signupRequest.getPhoneNum())
                    .star(signupRequest.getStar())
                    .name(signupRequest.getName())
                    .pwd(passwordEncoder.encode(signupRequest.getPwd()))
                    .email(signupRequest.getEmail())
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPwd()
                    )
            );
            var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
}
