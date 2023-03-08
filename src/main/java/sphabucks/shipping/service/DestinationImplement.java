package sphabucks.shipping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.repository.IDestinationRepo;
import sphabucks.shipping.vo.RequestDestination;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;

@RequiredArgsConstructor
@Service
public class DestinationImplement implements IDestinationService {

    private final IDestinationRepo iDestinationRepo;
    private final IUserRepository iUserRepository;

    @Override
    public void addDestination(RequestDestination requestDestination) {

        User user = iUserRepository.findByUserId(requestDestination.getUuid());   // 유저의 정보

        // 기본 배송지로 저장을 선택했거나 기존에 등록된 배송지가 없을 경우 기본 배송지 설정은 true 그 외에는 false
        boolean newDefaultDestination =
                (requestDestination.getDefaultDestination() || !iDestinationRepo.existsByUserUserId(user.getUserId()));

        // 새로운 배송지를 등록하는 경우
        if (newDefaultDestination) {
            // 기존에 등록되어있던 기본 배송지를 false로 변경해주어야 함
            // 기존에 등록된 배송지가 없는데 수정할 경우 에러발생 >> 등록된 배송지들이 있는 경우에만 수정되도록 if문 작성
            if (iDestinationRepo.existsByUserUserId(user.getUserId())) {
                Destination originalDefaultDestination =
                        iDestinationRepo.findByUserIdAndDefaultDestinationIsTrue(user.getId());
                originalDefaultDestination.setDefaultDestination(false);
            }
        }

        iDestinationRepo.save(Destination.builder()
                        .user(iUserRepository.findByUserId(requestDestination.getUuid()))
                        .name(requestDestination.getName())
                        .recipient(requestDestination.getRecipient())
                        .zipCode(requestDestination.getZipCode())
                        .defaultAddress(requestDestination.getDefaultAddress())
                        .detailAddress(requestDestination.getDetailAddress())
                        .phoneNum(requestDestination.getPhoneNum())
                        .phoneNum2(requestDestination.getPhoneNum2())
                        .content(requestDestination.getContent())
                        .defaultDestination(newDefaultDestination)
                .build());
    }

    @Override
    public Destination getDestination(Long id) {
        return iDestinationRepo.findById(id).get();
    }
}
