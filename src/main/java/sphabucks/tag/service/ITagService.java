package sphabucks.tag.service;

import sphabucks.tag.model.Tag;

public interface ITagService {
    Tag addTag(Tag tag);

    Tag getTag(Long id);
}
