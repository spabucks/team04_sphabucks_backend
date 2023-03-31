package sphabucks.domain.event.vo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestEventProductList {

    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Long eventId;

}
