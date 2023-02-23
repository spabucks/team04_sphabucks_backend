package sphabucks.event.vo;

import lombok.Data;

@Data
public class RequestEventImage {

    private Long eventId;
    private String image;
    private String alt;

}
