package sphabucks.domain.payments.coupons.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCoupon {
    private Long Id;
    private String name;
    private Date endDate;
    private String content;
    private String image;

}
