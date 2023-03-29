package sphabucks.domain.shipping.service;

import sphabucks.domain.shipping.vo.ResponseDestinationSummary;
import sphabucks.domain.shipping.model.Destination;
import sphabucks.domain.shipping.vo.RequestDestination;
import sphabucks.global.auth.vo.RequestHead;

import java.util.List;

public interface IDestinationService {

    // 배송지 추가
    void addDestination(RequestHead requestHead, RequestDestination requestDestination);

    // 배송지 관리를 클릭했을 때 해당 유저에게 저장된 모든 배송지를 보여줌
    List<ResponseDestinationSummary> getDestinationsByUUID(RequestHead requestHead);

    // 특정 배송지를 클릭했을 때 해당 배송지의 정보를 조회, 반환
    Destination getDestination(Long id);

    // 배송지 정보 업데이트
    void updateDestination(Long id, RequestDestination requestDestination);

    // 배송지 삭제
    void deleteDestination(Long id);
}
