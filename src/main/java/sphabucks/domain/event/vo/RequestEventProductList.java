package sphabucks.domain.event.vo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;

@Getter
public class RequestEventProductList {

    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Long eventId;

}
