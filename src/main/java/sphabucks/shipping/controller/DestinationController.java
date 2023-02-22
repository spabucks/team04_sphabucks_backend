package sphabucks.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.service.IDestinationService;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class DestinationController {
    private final IDestinationService iDestinationService;

    @PostMapping("/add")
    public Destination addDestination(@RequestBody Destination destination) {
        return iDestinationService.addPlace(destination);
    }

    @GetMapping("/get/{id}")
    public Destination getDestination(@PathVariable Long id) {
        return iDestinationService.getPlace(id);
    }
}
