package sphabucks.domain.users.service;

import sphabucks.domain.users.model.UserLikes;
import sphabucks.domain.users.vo.RequestUserLikes;
import sphabucks.global.auth.vo.RequestHead;

import java.util.List;

public interface IUserLikesService {
    void pushUserLikes(RequestHead requestHead, RequestUserLikes requestUserLikes);
    List<UserLikes> getUserLikes(RequestHead requestHead);
    List<UserLikes> getAll();
}
