package sphabucks.shipping.service;

import sphabucks.shipping.model.Destination;

public interface IDestinationService {
    Destination addPlace(Destination place);
    Destination getPlace(Long id);
}
