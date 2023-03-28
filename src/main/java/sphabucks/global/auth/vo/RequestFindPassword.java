package sphabucks.global.auth.vo;

import lombok.Getter;

@Getter
public class RequestFindPassword {
    private String loginId;
    private String email;
    private String name;
}
