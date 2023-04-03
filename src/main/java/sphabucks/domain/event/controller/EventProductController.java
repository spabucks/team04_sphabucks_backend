package sphabucks.domain.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.event.service.IEventProductService;
import sphabucks.domain.event.vo.RequestEventProductList;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/event-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "이벤트")
public class EventProductController {
    private final IEventProductService iEventProductService;

    @GetMapping("/get/recommendMD")
    @Operation(summary = "메인페이지 추천 MD")
    public ResponseEntity<Object> getRecommendMD() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, iEventProductService.recommendMD()));
    }

    @GetMapping("/getProductList/{id}")
    @Operation(summary = "이벤트 상품 조회")
    public ResponseEntity<Object> getEventProductList(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,
                iEventProductService.getEventProductList(id)));
    }
}
