package sphabucks.shipping.vo;

import lombok.Data;

@Data
public class RequestDestination {

    private String address;
    private String recipient;
    private String phoneNum;
    private String name;
    private Long userId;
}
