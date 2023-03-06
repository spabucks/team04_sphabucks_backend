package sphabucks.tag.service;

import sphabucks.tag.model.Tag;
import sphabucks.tag.vo.RequestTag;
import sphabucks.tag.vo.ResponseRecommendTag;

import java.util.HashMap;
import java.util.List;

public interface ITagService {
    void addTag(RequestTag requestTag);

    Tag getTag(Long id);

    List<Tag> getAll();

    List<ResponseRecommendTag> getRecommendTag();
}
