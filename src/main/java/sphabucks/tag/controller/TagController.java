package sphabucks.tag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.Tag;
import sphabucks.tag.service.ITagService;
import sphabucks.tag.vo.RequestTag;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private ITagService iTagService;

    @PostMapping("/add")
    public void addTag(@RequestBody RequestTag requestTag) {
        iTagService.addTag(requestTag);

    }

    @GetMapping("/get/{id}")
    public Tag getTag(@PathVariable Long id){
        return iTagService.getTag(id);
    }
}
