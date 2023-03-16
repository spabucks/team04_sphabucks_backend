package sphabucks.users.service;

import sphabucks.users.model.User;
import sphabucks.users.vo.RequestLoginIdCheck;
import sphabucks.users.vo.RequestUser;
import sphabucks.users.vo.ResponseUser;

import java.util.List;

public interface IUserService {

    void adduser(RequestUser requestUser);

    ResponseUser getUser(Long id);
    List<User> getAll();
    ResponseUser getUserByEmail(String email);
    ResponseUser getUserByLoginId(String loginId);

    Boolean existByLoginId(RequestLoginIdCheck requestLoginIdCheck);

}
