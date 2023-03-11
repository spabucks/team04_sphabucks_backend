package sphabucks.payments.cards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.payments.cards.model.Card;
import sphabucks.payments.cards.service.ICardService;
import sphabucks.payments.cards.vo.RequestCard;
import sphabucks.payments.cards.vo.ResponseCard;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CardController {
    private final ICardService iCardService;

    @PostMapping("/add")
    public void addCard(@RequestBody RequestCard requestCard) {
        iCardService.addCard(requestCard);
    }

    @GetMapping("/get/{id}")
    public ResponseCard getCard(@PathVariable Long id) {
        return iCardService.getCard(id);
    }
}
