package sphabucks.domain.tag.service;

import sphabucks.domain.tag.model.Tag;
import sphabucks.domain.tag.vo.RequestTag;
import sphabucks.domain.tag.vo.ResponseRecommendTag;

import java.util.List;

public interface ITagService {
    void addTag(RequestTag requestTag);

    Tag getTag(Long id);

    List<Tag> getAll();

    List<ResponseRecommendTag> getRecommendTag();
}
