package sphabucks.payments.gifticons.service;

import lombok.RequiredArgsConstructor;
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
    public ResponseGiftIcon addGiftIcon(RequestGiftIcon requestGiftIcon) {
        GiftIcon giftIcon = GiftIcon.builder()
                .name(requestGiftIcon.getName())
                .image(requestGiftIcon.getImage())
                .barcode(requestGiftIcon.getBarcode())
                .content(requestGiftIcon.getContent())
                .number(requestGiftIcon.getNumber())
                .alt(requestGiftIcon.getAlt())
                .build();

        GiftIcon resGiftIcon = iGiftIconRepository.save(giftIcon);

        ResponseGiftIcon responseGiftIcon = ResponseGiftIcon.builder()
                .Id(resGiftIcon.getId())
                .content(resGiftIcon.getContent())
                .number(resGiftIcon.getNumber())
                .build();

        return responseGiftIcon;
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
