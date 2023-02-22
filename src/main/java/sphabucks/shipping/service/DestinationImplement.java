package sphabucks.shipping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.repository.IDestinationRepo;
@RequiredArgsConstructor
@Service
public class DestinationImplement implements IDestinationService {

    private final IDestinationRepo iDestinationRepo;
    @Override
    public Destination addPlace(Destination place) {
        return iDestinationRepo.save(place);
    }

    @Override
    public Destination getPlace(Long id) {
        return iDestinationRepo.findById(id).get();
    }
}
