package sphabucks.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.event.model.Event;
import sphabucks.event.model.EventImage;
import sphabucks.event.model.EventProductList;
import sphabucks.event.service.IEventService;
import sphabucks.event.vo.RequestEvent;
import sphabucks.event.vo.RequestEventImage;
import sphabucks.event.vo.RequestEventProductList;
import sphabucks.event.vo.ResponseEventBanner;
import sphabucks.products.vo.ResponseProductList;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "이벤트")
@Slf4j
public class EventController {

    private final IEventService iEventService;

    @PostMapping("/v1/add")
    @Operation(summary = "이벤트 추가", description = "어드민 권한 - 아마 삭제될 수도?")
    public void addEvent(@RequestBody RequestEvent requestEvent){

        iEventService.addEvent(requestEvent);
    }

    @GetMapping("/v1/get/{id}")
    @Operation(summary = "이벤트 정보 조회", description = "이벤트 상품 조회가 아닌 이벤트 자체의 정보를 조회 - 아마 삭제될 수도?")
    public Event getEvent(@PathVariable Long id){
        log.info("input id ? {}",id);
        return iEventService.getEvent(id);
    }

    @GetMapping("/v1/get/all")
    @Operation(summary = "전체 이벤트 정보 조회", description = "이벤트 상품 조회가 아닌 이벤트 자체의 정보를 조회 - 아마 삭제될 수도?")
    public List<Event> getEventAll(){
        return iEventService.getEventAll();
    }

    @PostMapping("/v1/addImage")
    @Operation(summary = "이벤트 이미지 추가", description = "어드민 권한 - 아마 삭제될 수도?")
    public EventImage addEventImage(@RequestBody RequestEventImage requestEventImage) {

        return iEventService.addEventImage(requestEventImage);
    }

    @GetMapping("/v1/getImage/{id}")
    @Operation(summary = "이벤트 이미지 정보 조회")
    public EventImage getEventImage(@PathVariable Long id) {

        return  iEventService.getEventImage(id);
    }

    @GetMapping("/v1/get/banner")
    @Operation(summary = "메인페이지 이벤트 베너")
    public List<ResponseEventBanner> getEventBanner() {
        return iEventService.getEventBanner();
    }

}
