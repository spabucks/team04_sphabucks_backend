package sphabucks.users.service;

import sphabucks.users.model.User;
import sphabucks.users.vo.ResponseUser;

public interface IUserService {

    void adduser(User user);
    ResponseUser getUser(Long id);
}
