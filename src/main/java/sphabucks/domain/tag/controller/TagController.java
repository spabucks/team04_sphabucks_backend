package sphabucks.domain.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sphabucks.domain.tag.service.ITagService;
import sphabucks.domain.tag.vo.RequestTag;
import sphabucks.global.responseEntity.ResponseDTO;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "태그")
public class TagController {
    private final ITagService iTagService;

    @GetMapping("/get-recommendTag")
    @Operation(summary = "추천 태그", description = "돋보기 아이콘 클릭했을 때 뜨는 추천 태그 6개(랜덤 으로 선정)")
    public ResponseEntity<Object> getRecommendTag() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,iTagService.getRecommendTag()));
    }

    @GetMapping("/get/{id}")
    @Tag(name = "검색")
    @Operation(summary = "태그 조회")
    public ResponseEntity<Object> getTag(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,iTagService.getTag(id)));
    }

    @GetMapping("/get/all")
    @Operation(summary = "모든 태그 조회")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,iTagService.getAll()));
    }
}
