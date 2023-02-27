package sphabucks.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.tag.model.Tag;
import sphabucks.tag.repository.ITagRepository;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements ITagService{
    private final ITagRepository iTagRepository;


    @Override
    public Tag addTag(Tag tag) {
        return iTagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return iTagRepository.findById(id).get();
    }
}
