package sphabucks.global.auth.vo;

import lombok.Data;

@Data
public class RequestResetPassword {
    private String loginId;
    private String newPassword;
}
