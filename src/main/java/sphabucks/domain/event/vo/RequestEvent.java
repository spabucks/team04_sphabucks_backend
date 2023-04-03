package sphabucks.domain.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestEvent {
    private String season;  // 해당 이벤트 카테고리 이름(ex. 체리 블라썸, 발렌타인데이, ...)
    private String description; // 해당 이벤트 설명
    private Boolean isRecommend;    // 메인페이지 추천 MD에 띄우는 이벤트인지 아닌지
    private Date startDate; // 이벤트 시작 날짜
    private Date endDate;   // 이벤트 종료 날짜
}
