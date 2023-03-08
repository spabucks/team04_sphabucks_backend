package sphabucks.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;
import sphabucks.users.vo.RequestUser;
import sphabucks.users.vo.ResponseUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImplement implements IUserService{

    private final IUserRepository iUserRepository;

    @Override
    public void adduser(RequestUser requestUser) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(requestUser,User.class);
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

    @Override
    public List<User> getAll() {
        return iUserRepository.findAll();
    }

    @Override
    public ResponseUser getUserByEmail(String email) {
        Optional<User> user = iUserRepository.findByEmail(email);
        ResponseUser responseUser = ResponseUser.builder()
                .Id(user.get().getId())
                .email(user.get().getEmail())
                .name(user.get().getName())
                .nickname(user.get().getNickname())
                .build();

        return responseUser;
    }

    @Override
    public ResponseUser getUserByLoginId(String loginId) {
        Optional<User> user = iUserRepository.findByEmail(loginId);
        ResponseUser responseUser = ResponseUser.builder()
                .Id(user.get().getId())
                .email(user.get().getEmail())
                .name(user.get().getName())
                .nickname(user.get().getNickname())
                .build();

        return responseUser;
    }
}
