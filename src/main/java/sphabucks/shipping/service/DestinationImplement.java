package sphabucks.shipping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.repository.IDestinationRepo;
import sphabucks.shipping.vo.RequestDestination;

@RequiredArgsConstructor
@Service
public class DestinationImplement implements IDestinationService {

    private final IDestinationRepo iDestinationRepo;
    @Override
    public Destination addDestination(RequestDestination requestDestination) {
        Destination destination = Destination.builder()
                .name(requestDestination.getName())
                .address(requestDestination.getAddress())
                .phoneNum(requestDestination.getPhoneNum())
                .recipient(requestDestination.getRecipient())
                .userId(requestDestination.getUserId())
                .build();
        return iDestinationRepo.save(destination);
    }

    @Override
    public Destination getDestination(Long id) {
        return iDestinationRepo.findById(id).get();
    }
}
