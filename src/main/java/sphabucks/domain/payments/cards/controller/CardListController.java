package sphabucks.domain.payments.cards.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.payments.cards.service.ICardListService;
import sphabucks.domain.payments.cards.vo.RequestCardList;
import sphabucks.global.auth.vo.RequestHead;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/card-list")
@RequiredArgsConstructor
@Tag(name = "결제 수단")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CardListController {

    private final ICardListService iCardListService;

    @PostMapping("/add")
    @Operation(summary = "고객이 카드를 등록", description = "구현 X")
    public ResponseEntity<Object> addCardList(Authentication authentication, @RequestBody RequestCardList requestCardList) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iCardListService.addCardList(userId, requestCardList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "자신이 등록한 카드 확인", description = "구현 X")
    private ResponseEntity<Object> getCardList(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,iCardListService.getCardList(id)));
    }
}
