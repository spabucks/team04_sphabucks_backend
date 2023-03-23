package sphabucks.auth.vo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignUp {

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String userName; // 실명

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date birth;

    private String nickName;
}
