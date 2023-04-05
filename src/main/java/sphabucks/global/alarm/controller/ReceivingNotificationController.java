package sphabucks.global.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sphabucks.global.alarm.service.IReceivingNotificationService;
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
    @Operation(summary = "입고 알림 추가")
    public ResponseEntity<Object> addReceivingNotification(
            Authentication authentication,
            @RequestBody RequestReceivingNotification requestReceivingNotification){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        iReceivingNotificationService.addReceivingNotification(userId, requestReceivingNotification);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get")
    @Operation(summary = "입고 알림 리스트 ")
    public ResponseEntity<Object> getReceivingNotification(
            Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                HttpStatus.OK,
                iReceivingNotificationService.getReceivingNotification(userId)
        ));
    }

}
