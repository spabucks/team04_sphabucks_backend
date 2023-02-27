package sphabucks.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.service.IDestinationService;
import sphabucks.shipping.vo.RequestDestination;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class DestinationController {
    private final IDestinationService iDestinationService;

    @PostMapping("/add")
    public Destination addDestination(@RequestBody RequestDestination requestDestination) {
        return iDestinationService.addDestination(requestDestination);
    }

    @GetMapping("/get/{id}")
    public Destination getDestination(@PathVariable Long id) {
        return iDestinationService.getDestination(id);
    }
}
