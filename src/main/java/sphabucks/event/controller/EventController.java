package sphabucks.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sphabucks.event.model.Event;
import sphabucks.event.model.EventImage;
import sphabucks.event.model.EventProductList;
import sphabucks.event.repository.IEventImageRepository;
import sphabucks.event.repository.IEventProductListRepository;
import sphabucks.event.service.IEventService;
import sphabucks.event.vo.RequestEvent;
import sphabucks.event.vo.RequestEventImage;
import sphabucks.event.vo.RequestEventProductList;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final IEventService iEventService;

    @PostMapping("/add")
    public void addEvent(@RequestBody RequestEvent requestEvent){

        iEventService.addEvent(requestEvent);
    }

    @GetMapping("/get/{id}")
    public Event getEvent(@PathVariable Long id){
        log.info("input id ? {}",id);
        return iEventService.getEvent(id);
    }

    @PostMapping("/addImage")
    public EventImage addEventImage(@RequestBody RequestEventImage requestEventImage) {

        return iEventService.addEventImage(requestEventImage);
    }

    @GetMapping("/getImage/{id}")
    public EventImage getEventImage(@PathVariable Long id) {

        return  iEventService.getEventImage(id);
    }

    @PostMapping("/addProductList")
    public EventProductList addEventProductList(@RequestBody RequestEventProductList requestEventProductList) {

        return iEventService.addEventProductList(requestEventProductList);
    }

    @GetMapping("/getProductList/{id}")
    public EventProductList getEventProductList(@PathVariable Long id) {

        return iEventService.getEventProductList(id);
    }



}
