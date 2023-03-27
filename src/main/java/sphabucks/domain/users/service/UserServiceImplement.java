package sphabucks.domain.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.domain.users.repository.IUserRepository;
import sphabucks.domain.users.vo.RequestLoginIdCheck;
import sphabucks.exception.*;
import sphabucks.exception.ErrorHandler;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.vo.RequestUser;
import sphabucks.domain.users.vo.ResponseUser;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImplement implements IUserService{

    private final IUserRepository iUserRepository;

    @Override
    public void adduser(RequestUser requestUser) {

        // addUser는 사용하지 않는 메서드라서 에러처리 안해줬음,,
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(requestUser,User.class);
        user.setUserId(UUID.randomUUID().toString());
        iUserRepository.save(user);

    }

    @Override
    public ResponseUser getUser(Long id) {
        User user = iUserRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        ResponseUser responseUser = ResponseUser.builder()
                .Id(user.getId())
                .nickname(user.getNickname())
                .name(user.getName())
                .email(user.getEmail())
                .userId(user.getUserId())
                .build();

        return responseUser;
    }

    @Override
    public List<User> getAll() {

        if(iUserRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode());
        }

        return iUserRepository.findAll();
    }

    @Override
    public ResponseUser getUserByEmail(String email) {
        iUserRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND, ErrorCode.ACCOUNT_NOT_FOUND.getCode()));
        User user = iUserRepository.findByEmail(email)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));
        ResponseUser responseUser = ResponseUser.builder()
                .Id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();

        return responseUser;
    }

    @Override
    public ResponseUser getUserByLoginId(String loginId) {
        User user = iUserRepository.findByEmail(loginId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));
        ResponseUser responseUser = ResponseUser.builder()
                .Id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();

        return responseUser;
    }

    @Override
    public Boolean existByLoginId(RequestLoginIdCheck requestLoginIdCheck) {
        return iUserRepository.existsByLoginId(requestLoginIdCheck.getLoginId());
    }
}
