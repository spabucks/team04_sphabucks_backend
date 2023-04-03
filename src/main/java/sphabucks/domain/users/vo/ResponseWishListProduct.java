package sphabucks.domain.users.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseWishListProduct {

    private String title;
    private String imgUrl;
    private Long price;
}
