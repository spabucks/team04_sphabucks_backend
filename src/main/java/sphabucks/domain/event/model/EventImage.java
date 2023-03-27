package sphabucks.domain.event.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String image;   // 이벤트 이미지 url
    @Column(nullable = false)
    private String alt; // 해당 이미지에 대한 설명
    @Column(columnDefinition = "boolean default false")
    private Boolean chk;    // 메인, 서브이미지 구분 (true : 메인, false : 서브)
    @ManyToOne
    private Event event;

}
