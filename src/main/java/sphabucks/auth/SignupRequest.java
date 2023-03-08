package sphabucks.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequest {

    private String name;
    private String email;
    private String password;
    private String nickname;
    private String phoneNum;
    private String address;
    private Integer star;
    private String carNum;
    private Date birth;
    private boolean sex;    // {true: M, false: F}
    private String grade;

}
