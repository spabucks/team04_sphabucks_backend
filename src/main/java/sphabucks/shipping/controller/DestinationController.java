package sphabucks.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public void addDestination(@RequestBody RequestDestination requestDestination) {
        iDestinationService.addDestination(requestDestination);
    }

    @GetMapping("/get")
    public List<ResponseDestinationSummary> getDestinationsByUUID(@RequestBody String uuid) {
        return iDestinationService.getDestinationsByUUID(uuid);
    }
}
