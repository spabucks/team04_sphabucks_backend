package sphabucks.tag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.Tag;
import sphabucks.tag.service.ITagService;
import sphabucks.tag.vo.RequestTag;
import sphabucks.tag.vo.ResponseRecommendTag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class TagController {
    private final ITagService iTagService;

    @PostMapping("/add")
    public void addTag(@RequestBody RequestTag requestTag) {
        iTagService.addTag(requestTag);

    }

    @GetMapping("/get/{id}")
    public Tag getTag(@PathVariable Long id){
        return iTagService.getTag(id);
    }

    @GetMapping("/get/all")
    public List<Tag> getAll(){
        return iTagService.getAll();
    }

    @GetMapping("/get-recommendTag")
    public List<ResponseRecommendTag> getRecommendTag() {
        return iTagService.getRecommendTag();
    }
}
