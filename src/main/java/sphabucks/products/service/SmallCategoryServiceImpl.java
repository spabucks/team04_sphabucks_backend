package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.products.model.BigCategory;
import sphabucks.products.model.SmallCategory;
import sphabucks.products.repository.IBigCategoryRepository;
import sphabucks.products.repository.ISmallCategoryRepository;
import sphabucks.products.vo.RequestSmallCategory;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class SmallCategoryServiceImpl implements ISmallCategoryService{

    private final ISmallCategoryRepository iSmallCategoryRepository;
    private final IBigCategoryRepository iBigCategoryRepository;

    @Override
    public void addSmallCategory(RequestSmallCategory requestSmallCategory) {
        SmallCategory smallCategory = SmallCategory.builder()
                .bigCategory(iBigCategoryRepository.findById(requestSmallCategory.getBigCategoryId()).get())
                .name(requestSmallCategory.getName())
                .build();

        iSmallCategoryRepository.save(smallCategory);
    }

    @Override
    public SmallCategory getSmallCategory(Integer smallCategoryId) {

        return iSmallCategoryRepository.findById(smallCategoryId).get();
    }

    @Override
    public List<SmallCategory> getAll() {
        return iSmallCategoryRepository.findAll();
    }

    @Override
    public List<SmallCategory> getAllByType(BigCategory bigCategory) {
        return iSmallCategoryRepository.findById(bigCategory);
    }
}
