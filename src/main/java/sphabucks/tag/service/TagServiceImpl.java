package sphabucks.tag.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.tag.model.Tag;
import sphabucks.tag.repository.ITagRepository;
import sphabucks.tag.vo.RequestTag;
import sphabucks.tag.vo.ResponseRecommendTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements ITagService{
    private final ITagRepository iTagRepository;


    @Override
    public void addTag(RequestTag requestTag) {
        if(iTagRepository.findByName(requestTag.getName()).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_TAG,ErrorCode.DUPLICATE_TAG.getCode());
        }
        ModelMapper modelMapper = new ModelMapper();
        Tag tag = modelMapper.map(requestTag, Tag.class);
        iTagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        Tag tag = iTagRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode()));
        return tag;
    }

    @Override
    public List<Tag> getAll() {

        if(iTagRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode());
        }

        return iTagRepository.findAll();
    }

    @Override
    public List<ResponseRecommendTag> getRecommendTag() {
        List<ResponseRecommendTag> responseRecommendTags = new ArrayList<>();
        HashSet<Long> tagIds = new HashSet<>();
        while (tagIds.size() < 6) tagIds.add((long) (Math.random() * iTagRepository.count()) + 1);

        if(iTagRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode());
        }
        for (Long tagId : tagIds) responseRecommendTags.add(ResponseRecommendTag.builder()
                .id(tagId)
                .name(iTagRepository.findById(tagId)
                        .orElseThrow(()-> new BusinessException(ErrorCode.TAG_NOT_EXISTS, ErrorCode.TAG_NOT_EXISTS.getCode()))
                        .getName())
                .build());
        return responseRecommendTags;
    }
}
