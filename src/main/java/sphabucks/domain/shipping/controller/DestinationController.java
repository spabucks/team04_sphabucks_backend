package sphabucks.domain.shipping.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.shipping.vo.ResponseDestinationSummary;
import sphabucks.domain.shipping.model.Destination;
import sphabucks.domain.shipping.service.IDestinationService;
import sphabucks.domain.shipping.vo.RequestDestination;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
@Tag(name = "배송지")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class DestinationController {
    private final IDestinationService iDestinationService;

    @PostMapping("/add")
    @Operation(summary = "배송지 추가")
    public void addDestination(
            @RequestHeader String uuid,
            @RequestBody RequestDestination requestDestination) {

        iDestinationService.addDestination(uuid, requestDestination);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "배송지 수정 클릭 했을 때 뜨는 정보들")
    public Destination getDestination(@PathVariable Long id) {
        return iDestinationService.getDestination(id);
    }

    @GetMapping("/get")
    @Operation(summary = "배송지 관리 클릭했을 때 뜨는 배송지 리스트", description = "기본 배송지가 최상단, 그 이후로는 최근에 수정한 순으로 정렬되서 반환됨")
    public List<ResponseDestinationSummary> getDestinationsByUUID(@RequestHeader String uuid) {
        return iDestinationService.getDestinationsByUUID(uuid);
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "배송지 수정")
    public void updateDestination(
            @PathVariable Long id,
            @RequestBody RequestDestination requestDestination) {

        iDestinationService.updateDestination(id, requestDestination);
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "배송지 삭제")
    public void deleteDestination(@PathVariable Long id) {
        iDestinationService.deleteDestination(id);
    }
}
