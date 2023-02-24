package sphabucks.tag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.tag.model.Tag;
import sphabucks.tag.service.ITagService;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private ITagService iTagService;

    @PostMapping("/add")
    public Tag addTag(@RequestBody Tag tag) {
        return iTagService.addTag(tag);
    }

    @GetMapping("/get/{id}")
    public Tag getTag(@PathVariable Long id){
        return iTagService.getTag(id);
    }
}
