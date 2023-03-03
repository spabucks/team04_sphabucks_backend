package sphabucks.payments.cards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.cards.model.CardList;
import sphabucks.payments.cards.service.ICardListService;
import sphabucks.payments.cards.vo.RequestCardList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card-list")
@RequiredArgsConstructor
public class CardListController {

    private final ICardListService iCardListService;

    @PostMapping("/add")
    public void addCardList(@RequestBody RequestCardList requestCardList) {
        iCardListService.addCardList(requestCardList);
    }

    @GetMapping("/get/{id}")
    private List<CardList> getCardList(@PathVariable Long id) {
        return iCardListService.getCardList(id);
    }
}
