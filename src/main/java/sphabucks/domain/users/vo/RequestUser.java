package sphabucks.domain.users.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUser {
    private String loginId;
    private String name;
    private String email;
    private String pwd;
    private String nickname;
    private String phoneNum;
    private String address;
    private Long star;
    private String carNum;
    private Date birth;
    private boolean sex;    // {true: M, false: F}
    private String grade;


}
