package sphabucks.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.vo.RequestUser;
import sphabucks.users.vo.ResponseUser;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImplement implements IUserService{

    private final IUserRepository iUserRepository;
    @Override
    public ResponseUser adduser(RequestUser requestUser) {
        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .userId(uuid.toString())
                .name(requestUser.getName())
                .email(requestUser.getEmail())
                .password(requestUser.getPassword())
                .build();

        User resUser = iUserRepository.save(user);

        ResponseUser responseUser = ResponseUser.builder()
                .Id(resUser.getId())
                .name(resUser.getName())
                .email(resUser.getEmail())
                .nickname(resUser.getNickname())
                .build();

        return responseUser;
    }

    @Override
    public ResponseUser getUser(Long id) {
        User user = iUserRepository.findById(id).get();

        ResponseUser responseUser = ResponseUser.builder()
                .Id(user.getId())
                .nickname(user.getNickname())
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return responseUser;
    }
}
