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
@RequestMapping("/admin/api/v1/event-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "이벤트 상품 어드민")
public class AEventProductController {

    private final IEventProductService iEventProductService;

    @PostMapping("/addProductList")
    @Operation(summary = "이벤트 상품 추가", description = "어드민 권한")
    public ResponseEntity<Object> addEventProductList(@RequestBody RequestEventProductList requestEventProductList) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,
                iEventProductService.addEventProductList(requestEventProductList)));
    }
}
