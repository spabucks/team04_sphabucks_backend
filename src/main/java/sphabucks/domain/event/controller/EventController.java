package sphabucks.domain.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.event.service.IEventService;
import sphabucks.domain.event.vo.RequestEvent;
import sphabucks.domain.event.vo.RequestEventImage;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "이벤트")
@Slf4j
public class EventController {

    private final IEventService iEventService;

    @GetMapping("/get/{id}")
    @Operation(summary = "이벤트 정보 조회", description = "이벤트 상품 조회가 아닌 이벤트 자체의 정보를 조회 - 아마 삭제될 수도?")
    public ResponseEntity<Object> getEvent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iEventService.getEvent(id)));
    }

    @GetMapping("/get/all")
    @Operation(summary = "전체 이벤트 정보 조회", description = "이벤트 상품 조회가 아닌 이벤트 자체의 정보를 조회 - 아마 삭제될 수도?")
    public ResponseEntity<Object> getEventAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iEventService.getEventAll()));
    }

    @GetMapping("/getImage/{id}")
    @Operation(summary = "이벤트 이미지 정보 조회")
    public ResponseEntity<Object> getEventImage(@PathVariable Long id) {

        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iEventService.getEventImage(id)));
    }

    @GetMapping("/get/banner")
    @Operation(summary = "메인페이지 이벤트 베너")
    public ResponseEntity<Object> getEventBanner() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iEventService.getEventBanner()));
    }

}
