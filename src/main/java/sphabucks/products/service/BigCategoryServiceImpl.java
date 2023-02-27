package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.products.model.BigCategory;
import sphabucks.products.model.Category;
import sphabucks.products.repository.IBigCategoryRepository;
import sphabucks.products.repository.ICategoryRepository;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BigCategoryServiceImpl implements IBigCategoryService{

    private final IBigCategoryRepository iBigCategoryRepository;

    @Override
    public void addBigCategory(BigCategory bigCategory) {
        iBigCategoryRepository.save(bigCategory);
    }

    @Override
    public BigCategory getBigCategory(Integer bigCategoryId) {

        return iBigCategoryRepository.findById(bigCategoryId).get();
    }

    @Override
    public List<BigCategory> getAll() {
        return iBigCategoryRepository.findAll();
    }

}
