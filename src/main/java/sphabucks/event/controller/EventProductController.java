package sphabucks.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.event.model.EventProductList;
import sphabucks.event.service.IEventProductService;
import sphabucks.event.vo.RequestEventProductList;
import sphabucks.products.vo.ResponseProductList;

import java.util.List;

@RestController
@RequestMapping("/api/event-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "이벤트")
public class EventProductController {
    private final IEventProductService iEventProductService;

    @GetMapping("/v1/get/recommendMD")
    @Operation(summary = "메인페이지 추천 MD")
    public List<ResponseProductList> getRecommendMD() {
        return iEventProductService.recommendMD();
    }

    @PostMapping("/v1/addProductList")
    @Operation(summary = "이벤트 상품 추가", description = "어드민 권한 - 아마 삭제될 수도?")
    public EventProductList addEventProductList(@RequestBody RequestEventProductList requestEventProductList) {
        return iEventProductService.addEventProductList(requestEventProductList);
    }

    @GetMapping("/v1/getProductList/{id}")
    @Operation(summary = "이벤트 상품 조회")
    public EventProductList getEventProductList(@PathVariable Long id) {
        return iEventProductService.getEventProductList(id);
    }
}
