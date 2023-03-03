package sphabucks.payments.gifticons.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.payments.gifticons.model.GiftIcon;
import sphabucks.payments.gifticons.repository.IGiftIconRepository;
import sphabucks.payments.gifticons.vo.RequestGiftIcon;
import sphabucks.payments.gifticons.vo.ResponseGiftIcon;

@RequiredArgsConstructor
@Service
public class GiftIconServiceImpl implements IGiftIconService{
    private final IGiftIconRepository iGiftIconRepository;


    @Override
    public void addGiftIcon(RequestGiftIcon requestGiftIcon) {
        ModelMapper modelMapper = new ModelMapper();
        GiftIcon giftIcon = modelMapper.map(requestGiftIcon, GiftIcon.class);
        iGiftIconRepository.save(giftIcon);
    }

    @Override
    public ResponseGiftIcon getGiftIcon(Long id) {
        GiftIcon giftIcon = iGiftIconRepository.findById(id).get();

        ResponseGiftIcon responseGiftIcon = ResponseGiftIcon.builder()
                .Id(giftIcon.getId())
                .content(giftIcon.getContent())
                .number(giftIcon.getNumber())
                .build();

        return responseGiftIcon;
    }
}
