package sphabucks.auth.vo;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class RequestResetPassword {
    private String loginId;
    private String newPassword;
}
