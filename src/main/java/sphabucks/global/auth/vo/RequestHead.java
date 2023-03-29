package sphabucks.global.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestHead {

    // accessToken(jwt)
    private String aToken;

    // userUUID
    private String userId;
}
