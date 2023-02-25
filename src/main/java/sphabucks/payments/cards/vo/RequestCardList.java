package sphabucks.payments.cards.vo;

import lombok.Data;

@Data
public class RequestCardList {
    private Long userId;
    private Long cardId;
}
