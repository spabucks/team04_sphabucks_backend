package sphabucks.event.vo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;

@Getter
public class RequestEventImage {

    @Column(nullable = false)
    private Long eventId;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String alt;
}
