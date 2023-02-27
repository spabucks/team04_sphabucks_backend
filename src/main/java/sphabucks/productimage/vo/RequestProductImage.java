package sphabucks.productimage.vo;

import lombok.Getter;

@Getter
public class RequestProductImage {
    Long productId;
    Long imageId;
    String image;
    String alt;
    boolean chk;
}
