package sphabucks.domain.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.tag.service.ITagService;
import sphabucks.domain.tag.vo.RequestTag;

@RestController
@RequestMapping("/admin/api/v1/tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "태그 어드민")
public class ATagController {
    private final ITagService iTagService;

    @PostMapping("/add")
    @Operation(summary = "태그 추가", description = "어드민 권한")
    public ResponseEntity<Object> addTag(@RequestBody RequestTag requestTag) {
        iTagService.addTag(requestTag);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
