package sphabucks.event.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI 설정
    private Long id;
    @Column(nullable = false)
    private String eventImage;
    @Column(nullable = true)
    private String season;

}
