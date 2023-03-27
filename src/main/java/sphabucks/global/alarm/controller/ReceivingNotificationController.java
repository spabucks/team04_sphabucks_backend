package sphabucks.global.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.global.alarm.model.ReceivingNotification;
import sphabucks.global.alarm.service.IReceivingNotificationService;
import sphabucks.global.alarm.vo.RequestGetNotification;
import sphabucks.global.alarm.vo.RequestReceivingNotification;

import java.util.List;

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
    public void addReceivingNotification(@RequestBody RequestReceivingNotification requestReceivingNotification){
        iReceivingNotificationService.addReceivingNotification(requestReceivingNotification);
    }

    @PostMapping("/get")
    @Operation(summary = "입고 알림 리스트 ", description = "구현 완료")
    public List<ReceivingNotification> getReceivingNotification(@RequestBody RequestGetNotification requestGetNotification){
        log.info(requestGetNotification.getUserId());
        return iReceivingNotificationService.getReceivingNotification(requestGetNotification);
    }

}
