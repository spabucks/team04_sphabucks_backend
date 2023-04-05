package sphabucks.domain.shipping.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.domain.shipping.repository.IDestinationRepo;
import sphabucks.domain.shipping.vo.ResponseDestinationSummary;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.shipping.model.Destination;
import sphabucks.domain.shipping.vo.RequestDestination;
import sphabucks.domain.users.model.User;
import sphabucks.domain.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DestinationImplement implements IDestinationService {

    private final IDestinationRepo iDestinationRepo;
    private final IUserRepository iUserRepository;

    @Override
    public void addDestination(String userId, RequestDestination requestDestination) {

        User user = iUserRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        boolean newDefaultDestination =
                (requestDestination.getDefaultDestination() || !iDestinationRepo.existsByUserUserId(user.getUserId()));

        if (newDefaultDestination) {
            if (iDestinationRepo.findByUserIdAndDefaultDestinationIsTrue(user.getId()).isPresent()) {
                Destination originalDefaultDestination =
                        iDestinationRepo.findByUserIdAndDefaultDestinationIsTrue(user.getId()).get();
                originalDefaultDestination.setDefaultDestination(false);
            }
        }

        iDestinationRepo.save(Destination.builder()
                .user(user)
                .name(requestDestination.getName())
                .recipient(requestDestination.getRecipient())
                .defaultAddress(requestDestination.getDefaultAddress())
                .phoneNum(requestDestination.getPhoneNum())
                .content(requestDestination.getContent())
                .defaultDestination(newDefaultDestination)
                .build());
    }

    @Override
    public Destination getDestination(Long id) {
        return iDestinationRepo.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DESTINATION_NOT_EXISTS, ErrorCode.DESTINATION_NOT_EXISTS.getCode()));
    }

    @Override
    @Transactional
    public void updateDestination(Long id, RequestDestination requestDestination) {
        Destination destination = iDestinationRepo.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DESTINATION_NOT_EXISTS, ErrorCode.DESTINATION_NOT_EXISTS.getCode()));

        if (requestDestination.getDefaultDestination()) {
            Destination originalDefaultDestination =
                    iDestinationRepo.findByUserIdAndDefaultDestinationIsTrue(destination.getUser().getId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.DESTINATION_BASIC_NOT_EXISTS, ErrorCode.DESTINATION_BASIC_NOT_EXISTS.getDescription()));
            originalDefaultDestination.setDefaultDestination(false);
        }

        destination.setName(requestDestination.getName());
        destination.setRecipient(requestDestination.getRecipient());
        destination.setDefaultAddress(requestDestination.getDefaultAddress());
        destination.setPhoneNum(requestDestination.getPhoneNum());
        destination.setContent(requestDestination.getContent());
        destination.setDefaultDestination(requestDestination.getDefaultDestination());
    }

    @Override
    public void deleteDestination(Long id) {
        if (iDestinationRepo.findById(id).isEmpty()) {
            throw new BusinessException(ErrorCode.DESTINATION_NOT_EXISTS, ErrorCode.DESTINATION_NOT_EXISTS.getCode());
        }
        iDestinationRepo.deleteById(id);
    }

    @Override
    public List<ResponseDestinationSummary> getDestinationsByUUID(String userId) {
        List<ResponseDestinationSummary> return_value = new ArrayList<>();
        User user = iUserRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTS, ErrorCode.USER_NOT_EXISTS.getCode()));

        List<Destination> destinationList =
                iDestinationRepo.findAllByUserIdOrderByDefaultDestinationDescUpdateDateDesc(user.getId());

        destinationList.forEach(destination ->
                return_value.add(ResponseDestinationSummary.builder()
                        .shippingId(destination.getId())
                        .name(destination.getName())
                        .recipient(destination.getRecipient())
                        .defaultAddress(destination.getDefaultAddress())
                        .phoneNum(destination.getPhoneNum())
                        .content(destination.getContent())
                        .build()));

        return return_value;
    }
}
