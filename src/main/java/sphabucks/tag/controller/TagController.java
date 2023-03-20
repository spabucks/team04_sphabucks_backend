package sphabucks.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.Tag;
import sphabucks.tag.service.ITagService;
import sphabucks.tag.vo.RequestTag;
import sphabucks.tag.vo.ResponseRecommendTag;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@io.swagger.v3.oas.annotations.tags.Tag(name = "태그")    // 태그 모델 때문에 이름 겹쳐서 이렇게 작성해야하네요
public class TagController {
    private final ITagService iTagService;

    @PostMapping("/v1/add")
    @Operation(summary = "태그 추가", description = "어드민 권한 - 삭제 예정?")
    public void addTag(@RequestBody RequestTag requestTag) {
        iTagService.addTag(requestTag);

    }

    @GetMapping("/v1/get/{id}")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "검색")
    @Operation(summary = "태그 조회", description = "삭제 예정")
    public Tag getTag(@PathVariable Long id){
        return iTagService.getTag(id);
    }

    @GetMapping("/v1/get/all")
    @Operation(summary = "모든 태그 조회", description = "필요 있나?")
    public List<Tag> getAll(){
        return iTagService.getAll();
    }

    @GetMapping("/v1/get-recommendTag")
    @Operation(summary = "추천 태그", description = "돋보기 아이콘 클릭했을 때 뜨는 추천 태그 6개(랜덤 으로 선정)")
    public List<ResponseRecommendTag> getRecommendTag() {
        return iTagService.getRecommendTag();
    }
}
