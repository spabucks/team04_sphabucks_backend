package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sphabucks.error.BusinessException;
import sphabucks.error.ErrorCode;
import sphabucks.products.model.BigCategory;
import sphabucks.products.repository.IBigCategoryRepository;
import sphabucks.products.vo.RequestBigCategory;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BigCategoryServiceImpl implements IBigCategoryService{

    private final IBigCategoryRepository iBigCategoryRepository;

    @Override
    public void addBigCategory(RequestBigCategory requestBigCategory) {
        if(iBigCategoryRepository.findByName(requestBigCategory.getName()).isPresent()){
            throw new BusinessException(ErrorCode.DUPLICATE_CATEGORY, ErrorCode.DUPLICATE_CATEGORY.getCode());
        }
        ModelMapper modelMapper = new ModelMapper();
        BigCategory bigCategory = modelMapper.map(requestBigCategory, BigCategory.class);

        iBigCategoryRepository.save(bigCategory);
    }

    @Override
    public BigCategory getBigCategory(Long bigCategoryId) {

        BigCategory bigCategory = iBigCategoryRepository.findById(bigCategoryId)
                .orElseThrow(()->new BusinessException(ErrorCode.CATEGORY_NOT_EXISTS, ErrorCode.CATEGORY_NOT_EXISTS.getCode()));

        return bigCategory;
    }

    @Override
    public List<BigCategory> getAll() {
        return iBigCategoryRepository.findAll();
    }

}
