package sphabucks.event.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEventBanner {
    private Long eventId;
    private String name;
    private String imageUrl;
}
