package sphabucks.email.vo;

import lombok.Data;
import lombok.Getter;

@Data
public class RequestEmailCheck {

    private String email;
    private String code;
}
