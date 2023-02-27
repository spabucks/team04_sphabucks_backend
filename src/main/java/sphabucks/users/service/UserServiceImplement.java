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
    public void adduser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        iUserRepository.save(user);
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
