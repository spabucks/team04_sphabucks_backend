package sphabucks.users.service;

import sphabucks.users.vo.RequestUser;
import sphabucks.users.vo.ResponseUser;

public interface IUserService {

    ResponseUser adduser(RequestUser requestUser);
    ResponseUser getUser(Long id);
}
