package sphabucks.domain.users.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseWishList {

    private Long id;
    private Long productId;
}
