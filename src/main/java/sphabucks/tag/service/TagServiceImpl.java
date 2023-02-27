package sphabucks.tag.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.tag.model.Tag;
import sphabucks.tag.repository.ITagRepository;
import sphabucks.tag.vo.RequestTag;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements ITagService{
    private final ITagRepository iTagRepository;


    @Override
    public void addTag(RequestTag requestTag) {

        ModelMapper modelMapper = new ModelMapper();
        Tag tag = modelMapper.map(requestTag, Tag.class);
        iTagRepository.save(tag);

    }

    @Override
    public Tag getTag(Long id) {
        return iTagRepository.findById(id).get();
    }
}
