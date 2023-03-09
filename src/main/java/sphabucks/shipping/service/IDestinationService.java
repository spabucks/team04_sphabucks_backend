package sphabucks.shipping.service;

import sphabucks.shipping.model.Destination;
import sphabucks.shipping.vo.RequestDestination;
import sphabucks.shipping.vo.ResponseDestinationSummary;

import java.util.List;

public interface IDestinationService {
    void addDestination(RequestDestination requestDestination);
    Destination getDestinations(Long id);
    List<ResponseDestinationSummary> getDestinationsByUUID(String uuid);
}
