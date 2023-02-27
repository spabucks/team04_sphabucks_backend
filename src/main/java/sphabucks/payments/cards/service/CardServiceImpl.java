package sphabucks.payments.cards.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.payments.cards.model.Card;
import sphabucks.payments.cards.repository.ICardRepo;
import sphabucks.payments.cards.vo.RequestCard;
import sphabucks.payments.cards.vo.ResponseCard;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements ICardService{

    private final ICardRepo iCardRepo;
    @Override
    public void addCard(RequestCard requestCard) {
        ModelMapper modelMapper = new ModelMapper();
        Card card = modelMapper.map(requestCard, Card.class);
        card.setMoney(card.getDefaultMoney());
        iCardRepo.save(card);
    }

    @Override
    public ResponseCard getCard(Long id) {
        Card card = iCardRepo.findById(id).get();

        return ResponseCard.builder()
                .name(card.getName())
                .image(card.getImage())
                .money(card.getMoney())
                .build();
    }
}
