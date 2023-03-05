package sphabucks.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sphabucks.event.service.IEventProductService;
import sphabucks.products.vo.ResponseProductList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class EventProductController {
    private final IEventProductService iEventProductService;

    @GetMapping("/get/recommendMD")
    public List<ResponseProductList> getRecommendMD() {
        return iEventProductService.recommendMD();
    }
}
