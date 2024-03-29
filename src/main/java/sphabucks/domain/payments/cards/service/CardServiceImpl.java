package sphabucks.domain.payments.cards.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.domain.payments.cards.repository.ICardRepo;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.payments.cards.model.Card;
import sphabucks.domain.payments.cards.vo.RequestCard;
import sphabucks.domain.payments.cards.vo.ResponseCard;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements ICardService{

    private final ICardRepo iCardRepo;
    @Override
    public void addCard(RequestCard requestCard) {

        iCardRepo.findByNumber(requestCard.getNumber())
                .orElseThrow(()->new BusinessException(ErrorCode.DUPLICATE_CARD,ErrorCode.DUPLICATE_CARD.getCode()));

        ModelMapper modelMapper = new ModelMapper();
        Card card = modelMapper.map(requestCard, Card.class);
        card.setMoney(card.getDefaultMoney());
        iCardRepo.save(card);
    }

    @Override
    public ResponseCard getCard(Long id) {

        Card card = iCardRepo.findById(id)
                .orElseThrow(()->new BusinessException(ErrorCode.CARD_NOT_EXISTS, ErrorCode.CARD_NOT_EXISTS.getCode()));

        return ResponseCard.builder()
                .name(card.getName())
                .image(card.getImage())
                .money(card.getMoney())
                .build();
    }
}
