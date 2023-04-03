package sphabucks.domain.users.service;

import sphabucks.domain.users.vo.RequestLoginIdCheck;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.vo.RequestUser;
import sphabucks.domain.users.vo.ResponseUser;

import java.util.List;

public interface IUserService {

    void adduser(RequestUser requestUser);

    ResponseUser getUser(Long id);
    List<User> getAll();
    ResponseUser getUserByEmail(String email);
    ResponseUser getUserByLoginId(String loginId);

    Boolean existByLoginId(RequestLoginIdCheck requestLoginIdCheck);

}
