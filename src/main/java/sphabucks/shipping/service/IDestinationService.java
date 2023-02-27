package sphabucks.shipping.service;

import sphabucks.shipping.model.Destination;
import sphabucks.shipping.vo.RequestDestination;

public interface IDestinationService {
    Destination addDestination(RequestDestination requestDestination);
    Destination getDestination(Long id);
}
