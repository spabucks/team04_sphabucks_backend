package sphabucks.domain.users.service;

import sphabucks.domain.users.model.UserLikes;
import sphabucks.domain.users.vo.RequestUserLikes;
import sphabucks.domain.users.vo.ResponseUserLikes;

import java.util.List;

public interface IUserLikesService {
    void pushUserLikes(String userId, RequestUserLikes requestUserLikes);
    List<ResponseUserLikes> getUserLikes(String userId);
    List<UserLikes> getAll();
}
