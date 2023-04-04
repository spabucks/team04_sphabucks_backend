package sphabucks.domain.users.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserLikes {

    Long productId;
    String name;
    Long price;
    String productImage;
    Long likeCount;

}
