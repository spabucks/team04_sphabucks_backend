package sphabucks.domain.shipping.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.shipping.service.IDestinationService;
import sphabucks.domain.shipping.vo.RequestDeleteDestination;
import sphabucks.domain.shipping.vo.RequestDestination;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
@Tag(name = "배송지")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class DestinationController {
    private final IDestinationService iDestinationService;

    @PostMapping("/add")
    @Operation(summary = "배송지 추가")
    public ResponseEntity<Object> addDestination(
            Authentication authentication,
            @RequestBody RequestDestination requestDestination) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iDestinationService.addDestination(userId, requestDestination);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "배송지 수정 클릭 했을 때 뜨는 정보들")
    public ResponseEntity<Object> getDestination(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,iDestinationService.getDestination(id)));
    }

    @GetMapping("/get")
    @Operation(summary = "배송지 관리 클릭했을 때 뜨는 배송지 리스트", description = "기본 배송지가 최상단, 그 이후로는 최근에 수정한 순으로 정렬되서 반환됨")
    public ResponseEntity<Object> getDestinationsByUUID(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK,iDestinationService.getDestinationsByUUID(userId)));
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "배송지 수정")
    public ResponseEntity<Object> updateDestination(
            @PathVariable Long id,
            @RequestBody RequestDestination requestDestination) {

        iDestinationService.updateDestination(id, requestDestination);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/delete")
    @Operation(summary = "배송지 삭제")
    public ResponseEntity<Object> deleteDestination(
            Authentication authentication,
            @RequestBody RequestDeleteDestination requestDeleteDestination) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iDestinationService.deleteDestination(requestDeleteDestination.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
