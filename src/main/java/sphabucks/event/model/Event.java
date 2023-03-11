package sphabucks.event.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI 설정
    private Long id;
    @Column(nullable = false)
    private String season;  // 해당 이벤트 카테고리 이름(ex. 체리 블라썸, 발렌타인데이, ...)
    @Column(nullable = false)
    private String description; // 해당 이벤트 설명
    @Column(columnDefinition = "boolean default false")
    private Boolean isRecommend;    // 메인페이지 추천 MD에 띄우는 이벤트인지 아닌지
    @Column(nullable = false)
    private Date startDate; // 이벤트 시작 날짜
    @Column(nullable = false)
    private Date endDate;   // 이벤트 종료 날짜

}
