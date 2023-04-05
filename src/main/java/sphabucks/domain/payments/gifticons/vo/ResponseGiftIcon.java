package sphabucks.domain.payments.gifticons.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGiftIcon {
    private Long Id;
    private String content;
    private String number;
}
