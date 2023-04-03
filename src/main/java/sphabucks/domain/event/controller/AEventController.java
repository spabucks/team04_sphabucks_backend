package sphabucks.domain.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.event.service.IEventService;
import sphabucks.domain.event.vo.RequestEvent;
import sphabucks.domain.event.vo.RequestEventImage;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/admin/api/v1/event")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "이벤트 어드민")
public class AEventController {

    private final IEventService iEventService;

    @PostMapping("/add")
    @Operation(summary = "이벤트 추가", description = "어드민 권한 - 아마 삭제될 수도?")
    public ResponseEntity<Object> addEvent(@RequestBody RequestEvent requestEvent){
        iEventService.addEvent(requestEvent);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/addImage")
    @Operation(summary = "이벤트 이미지 추가", description = "어드민 권한 - 아마 삭제될 수도?")
    public ResponseEntity<Object> addEventImage(@RequestBody RequestEventImage requestEventImage) {

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iEventService.addEventImage(requestEventImage)));
    }
}
