package sphabucks.users.service;

import sphabucks.users.model.User;
import sphabucks.users.vo.RequestUser;
import sphabucks.users.vo.ResponseUser;

import java.util.List;

public interface IUserService {

    void adduser(RequestUser requestUser);

    ResponseUser getUser(Long id);
    List<User> getAll();
}
