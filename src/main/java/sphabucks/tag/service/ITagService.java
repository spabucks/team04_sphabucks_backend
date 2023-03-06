package sphabucks.tag.service;

import sphabucks.tag.model.Tag;
import sphabucks.tag.vo.RequestTag;

import java.util.List;

public interface ITagService {
    void addTag(RequestTag requestTag);

    Tag getTag(Long id);

    List<Tag> getAll();
}
