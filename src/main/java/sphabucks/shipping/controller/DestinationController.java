package sphabucks.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.service.IDestinationService;
import sphabucks.shipping.vo.RequestDestination;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class DestinationController {
    private final IDestinationService iDestinationService;

    @PostMapping("/add")
    public void addDestination(@RequestBody RequestDestination requestDestination) {
        iDestinationService.addDestination(requestDestination);
    }

    @GetMapping("/get/{id}")
    public Destination getDestination(@PathVariable Long id) {
        return iDestinationService.getDestination(id);
    }
}
