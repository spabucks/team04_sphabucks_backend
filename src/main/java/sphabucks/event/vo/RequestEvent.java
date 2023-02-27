package sphabucks.event.vo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class RequestEvent {

    @Column(nullable = false)
    private String eventImage;
    @Column(nullable = true)
    private String season;
}
