package sphabucks.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.shipping.model.Destination;
import sphabucks.shipping.service.IDestinationService;
import sphabucks.shipping.vo.RequestDestination;
import sphabucks.shipping.vo.ResponseDestinationSummary;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class DestinationController {
    private final IDestinationService iDestinationService;

    @PostMapping("/add")
    public void addDestination(
            @RequestHeader String uuid,
            @RequestBody RequestDestination requestDestination) {

        iDestinationService.addDestination(uuid, requestDestination);
    }

    @GetMapping("/get/{id}")
    public Destination getDestination(@PathVariable Long id) {
        return iDestinationService.getDestination(id);
    }

    @GetMapping("/get")
    public List<ResponseDestinationSummary> getDestinationsByUUID(@RequestHeader String uuid) {
        return iDestinationService.getDestinationsByUUID(uuid);
    }

    @PostMapping("/update/{id}")
    public void updateDestination(
            @PathVariable Long id,
            @RequestBody RequestDestination requestDestination) {

        iDestinationService.updateDestination(id, requestDestination);
    }
}
