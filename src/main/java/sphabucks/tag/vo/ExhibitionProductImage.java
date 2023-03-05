package sphabucks.tag.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ExhibitionProductImage {
    private Long productId;
    private String image;
    private String alt;
    private Integer chk;
}
