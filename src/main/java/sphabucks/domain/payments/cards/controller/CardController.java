package sphabucks.domain.payments.cards.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.cards.service.ICardService;
import sphabucks.domain.payments.cards.vo.ResponseCard;
import sphabucks.domain.payments.cards.vo.RequestCard;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CardController {
    private final ICardService iCardService;

    @PostMapping("/add")
    @Operation(summary = "스타벅스 카드 추가", description = "어드민 권한 - 삭제 예정?")
    public void addCard(@RequestBody RequestCard requestCard) {
        iCardService.addCard(requestCard);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "스타벅스 카드 조회", description = "사용자가 등록한 카드 정보 조회?")
    public ResponseCard getCard(@PathVariable Long id) {
        return iCardService.getCard(id);
    }
}
