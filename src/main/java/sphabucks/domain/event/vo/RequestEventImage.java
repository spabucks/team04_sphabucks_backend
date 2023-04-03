package sphabucks.domain.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestEventImage {

    private Long eventId;
    private String image;
    private String alt;
}
