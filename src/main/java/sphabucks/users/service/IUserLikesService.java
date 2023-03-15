package sphabucks.users.service;

import sphabucks.users.model.UserLikes;
import sphabucks.users.vo.RequestUserLikes;

import java.util.List;

public interface IUserLikesService {
    void pushUserLikes(RequestUserLikes requestUserLikes);
    List<UserLikes> getUserLikes(String userId);
    List<UserLikes> getAll();
}
