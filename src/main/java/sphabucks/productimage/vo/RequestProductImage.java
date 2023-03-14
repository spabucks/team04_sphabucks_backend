package sphabucks.productimage.vo;

import lombok.Getter;

@Getter
public class RequestProductImage {
    Long productId;
    String image;
    String alt;
    Long chk;
}
