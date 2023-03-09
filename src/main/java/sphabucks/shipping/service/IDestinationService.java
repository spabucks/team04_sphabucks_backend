package sphabucks.shipping.service;

import sphabucks.shipping.model.Destination;
import sphabucks.shipping.vo.RequestDestination;
import sphabucks.shipping.vo.ResponseDestinationSummary;

import java.util.List;

public interface IDestinationService {

    // 배송지 추가
    void addDestination(String uuid, RequestDestination requestDestination);

    // 배송지 관리를 클릭했을 때 해당 유저에게 저장된 모든 배송지를 보여줌
    List<ResponseDestinationSummary> getDestinationsByUUID(String uuid);

    // 특정 배송지를 클릭했을 때 해당 배송지의 정보를 조회, 반환
    Destination getDestination(Long id);

    // 배송지 정보 업데이트
    void updateDestination(Long id, RequestDestination requestDestination);

}
