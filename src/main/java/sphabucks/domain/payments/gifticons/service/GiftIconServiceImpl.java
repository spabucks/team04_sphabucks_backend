package sphabucks.domain.payments.gifticons.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.domain.payments.gifticons.vo.RequestGiftIcon;
import sphabucks.domain.payments.gifticons.vo.ResponseGiftIcon;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.payments.gifticons.model.GiftIcon;
import sphabucks.domain.payments.gifticons.repository.IGiftIconRepository;

@RequiredArgsConstructor
@Service
public class GiftIconServiceImpl implements IGiftIconService{
    private final IGiftIconRepository iGiftIconRepository;


    @Override
    public void addGiftIcon(RequestGiftIcon requestGiftIcon) {

        if(iGiftIconRepository.findByNumber(requestGiftIcon.getNumber()).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_GIFTICON, ErrorCode.DUPLICATE_GIFTICON.getCode());
        }

        ModelMapper modelMapper = new ModelMapper();
        GiftIcon giftIcon = modelMapper.map(requestGiftIcon, GiftIcon.class);
        iGiftIconRepository.save(giftIcon);
    }

    @Override
    public ResponseGiftIcon getGiftIcon(Long id) {

        GiftIcon giftIcon = iGiftIconRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.GIFTICON_NOT_EXISTS, ErrorCode.GIFTICON_NOT_EXISTS.getCode()));

        ResponseGiftIcon responseGiftIcon = ResponseGiftIcon.builder()
                .Id(giftIcon.getId())
                .content(giftIcon.getContent())
                .number(giftIcon.getNumber())
                .build();

        return responseGiftIcon;
    }
}
