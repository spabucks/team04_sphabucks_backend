package sphabucks.domain.shipping.service;

import sphabucks.domain.shipping.vo.ResponseDestinationSummary;
import sphabucks.domain.shipping.model.Destination;
import sphabucks.domain.shipping.vo.RequestDestination;

import java.util.List;

public interface IDestinationService {

    void addDestination(String userId, RequestDestination requestDestination);
    List<ResponseDestinationSummary> getDestinationsByUUID(String userId);

    Destination getDestination(Long id);

    void updateDestination(Long id, RequestDestination requestDestination);

    void deleteDestination(Long id);
}
