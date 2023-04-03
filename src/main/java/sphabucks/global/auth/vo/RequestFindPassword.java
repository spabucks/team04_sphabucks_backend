package sphabucks.global.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestFindPassword {
    private String loginId;
    private String email;
    private String name;
}
