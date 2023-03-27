package sphabucks.domain.payments.cards.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.cards.model.CardList;
import sphabucks.domain.payments.cards.service.ICardListService;
import sphabucks.domain.payments.cards.vo.RequestCardList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card-list")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CardListController {

    private final ICardListService iCardListService;

    @PostMapping("/add")
    @Operation(summary = "고객이 카드를 등록", description = "구현 X")
    public void addCardList(@RequestBody RequestCardList requestCardList) {
        iCardListService.addCardList(requestCardList);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "자신이 등록한 카드 확인", description = "구현 X")
    private List<CardList> getCardList(@PathVariable Long id) {
        return iCardListService.getCardList(id);
    }
}
