package sphabucks.shipping.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.repository.IDestinationRepo;
import sphabucks.shipping.vo.RequestDestination;
import sphabucks.shipping.vo.ResponseDestinationSummary;
import sphabucks.users.model.User;
import sphabucks.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DestinationImplement implements IDestinationService {

    private final IDestinationRepo iDestinationRepo;
    private final IUserRepository iUserRepository;

    @Override
    public void addDestination(String uuid, RequestDestination requestDestination) {

        User user = iUserRepository.findByUserId(uuid);   // 유저의 정보

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
                .user(user)
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

    @Override
    @Transactional
    public void updateDestination(Long id, RequestDestination requestDestination) { // id : 배송지의 고유 id(인덱스번호)
        Destination destination = iDestinationRepo.findById(id).get();

        if (requestDestination.getDefaultDestination()) {   // 기본 배송지로 저장을 체크했을 경우 기존의 기본 배송지를 false로 변경
            Destination originalDefaultDestination =
                    iDestinationRepo.findByUserIdAndDefaultDestinationIsTrue(destination.getUser().getId());
            originalDefaultDestination.setDefaultDestination(false);
        }

        destination.setName(requestDestination.getName());
        destination.setRecipient(requestDestination.getRecipient());
        destination.setZipCode(requestDestination.getZipCode());
        destination.setDefaultAddress(requestDestination.getDefaultAddress());
        destination.setDetailAddress(requestDestination.getDetailAddress());
        destination.setPhoneNum(requestDestination.getPhoneNum());
        destination.setPhoneNum2(requestDestination.getPhoneNum2());
        destination.setContent(requestDestination.getContent());
        destination.setDefaultDestination(requestDestination.getDefaultDestination());
    }

    @Override
    public void deleteDestination(Long id) {
        // 어차피 기본 배송지는 삭제가 불가능하므로 삭제하려는 배송지는 바로 삭제되어도 무관함
        // 기본 배송지는 삭제 버튼이 표시 되지 않으므로 기본배송지가 삭제되는 경우는 발생하지 않음
        iDestinationRepo.deleteById(id);
    }

    @Override
    public List<ResponseDestinationSummary> getDestinationsByUUID(String uuid) {
        List<ResponseDestinationSummary> return_value = new ArrayList<>();  // 최종 반환될 리스트
        User user = iUserRepository.findByUserId(uuid); // 조회할 유저
        List<Destination> destinationList = // 유저의 정보에 저장된 모든 배송지
                iDestinationRepo.findAllByUserIdOrderByDefaultDestinationDescUpdateDateDesc(user.getId());

        for (Destination destination : destinationList)
            return_value.add(ResponseDestinationSummary.builder()
                    .name(destination.getName())
                    .recipient(destination.getRecipient())
                    .zipCode(destination.getZipCode())
                    .defaultAddress(destination.getDefaultAddress())
                    .detailAddress(destination.getDetailAddress())
                    .phoneNum(destination.getPhoneNum())
                    .phoneNum2(destination.getPhoneNum2())
                    .content(destination.getContent())
                    .build());

        return return_value;
    }
}
