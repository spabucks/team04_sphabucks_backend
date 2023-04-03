package sphabucks.domain.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.global.exception.BusinessException;
import sphabucks.global.exception.ErrorCode;
import sphabucks.domain.products.model.BigCategory;
import sphabucks.domain.products.model.SmallCategory;
import sphabucks.domain.products.repository.IBigCategoryRepository;
import sphabucks.domain.products.repository.ISmallCategoryRepository;
import sphabucks.domain.products.vo.RequestSmallCategory;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class SmallCategoryServiceImpl implements ISmallCategoryService{

    private final ISmallCategoryRepository iSmallCategoryRepository;
    private final IBigCategoryRepository iBigCategoryRepository;

    @Override
    public void addSmallCategory(RequestSmallCategory requestSmallCategory) {

        if(iSmallCategoryRepository.findByName(requestSmallCategory.getName()).isPresent()){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }

        SmallCategory smallCategory = SmallCategory.builder()
                .bigCategory(iBigCategoryRepository.findById(requestSmallCategory.getBigCategoryId())
                        .orElseThrow(()-> new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode())))
                .name(requestSmallCategory.getName())
                .build();

        iSmallCategoryRepository.save(smallCategory);
    }

    @Override
    public SmallCategory getSmallCategory(Long smallCategoryId) {

        SmallCategory smallCategory = iSmallCategoryRepository.findById(smallCategoryId)
                .orElseThrow(()-> new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode()));

        return smallCategory;
    }

    @Override
    public List<SmallCategory> getAll() {

        if(iSmallCategoryRepository.findAll().isEmpty()){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }

        return iSmallCategoryRepository.findAll();
    }

    @Override
    public List<SmallCategory> getAllByType(BigCategory bigCategory) {

        if(iSmallCategoryRepository.findById(bigCategory).isEmpty()){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode());
        }

        return iSmallCategoryRepository.findById(bigCategory);
    }
}
