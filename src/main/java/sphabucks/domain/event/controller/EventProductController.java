package sphabucks.domain.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.event.model.EventProductList;
import sphabucks.domain.event.service.IEventProductService;
import sphabucks.domain.event.vo.RequestEventProductList;
import sphabucks.domain.products.vo.ResponseProductList;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "이벤트")
public class EventProductController {
    private final IEventProductService iEventProductService;

    @GetMapping("/get/recommendMD")
    @Operation(summary = "메인페이지 추천 MD")
    public List<ResponseProductList> getRecommendMD() {
        return iEventProductService.recommendMD();
    }

    @PostMapping("/addProductList")
    @Operation(summary = "이벤트 상품 추가", description = "어드민 권한 - 아마 삭제될 수도?")
    public EventProductList addEventProductList(@RequestBody RequestEventProductList requestEventProductList) {
        return iEventProductService.addEventProductList(requestEventProductList);
    }

    @GetMapping("/getProductList/{id}")
    @Operation(summary = "이벤트 상품 조회")
    public EventProductList getEventProductList(@PathVariable Long id) {
        return iEventProductService.getEventProductList(id);
    }
}
