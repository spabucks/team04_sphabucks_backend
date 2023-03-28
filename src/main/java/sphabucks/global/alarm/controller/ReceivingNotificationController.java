package sphabucks.global.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.global.alarm.service.IReceivingNotificationService;
import sphabucks.global.alarm.vo.RequestGetNotification;
import sphabucks.global.alarm.vo.RequestReceivingNotification;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
@Tag(name = "입고 알림")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Slf4j
public class ReceivingNotificationController {

    private final IReceivingNotificationService iReceivingNotificationService;

    @PostMapping("/add")
    @Operation(summary = "입고 알림 추가", description = "구현 완료")
    public ResponseEntity<Object> addReceivingNotification(
            @RequestBody RequestReceivingNotification requestReceivingNotification){
        iReceivingNotificationService.addReceivingNotification(requestReceivingNotification);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/get")
    @Operation(summary = "입고 알림 리스트 ", description = "구현 완료")
    public ResponseEntity<Object> getReceivingNotification(
            @RequestBody RequestGetNotification requestGetNotification){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                HttpStatus.OK,
                iReceivingNotificationService.getReceivingNotification(requestGetNotification)
        ));
    }

}
