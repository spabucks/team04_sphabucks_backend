package sphabucks.users.vo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class RequestUser {
    private String name;
    private String email;
    private String pwd;
    private String nickname;
    private String phoneNum;
    private String address;
    private Integer star;
    private String carNum;
    private Date birth;
    private boolean sex;    // {true: M, false: F}
    private String grade;


}
