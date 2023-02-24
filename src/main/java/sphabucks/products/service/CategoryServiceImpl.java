package sphabucks.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sphabucks.products.model.Category;
import sphabucks.products.repository.ICategoryRepository;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private final ICategoryRepository iCategoryRepository;

    @Override
    public void addCategory(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public Category getCategory(Integer categoryId) {

        return iCategoryRepository.findById(categoryId).get();
    }

    @Override
    public List<Category> getAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public List<Category> getAllByType(String categoryType) {
        return null;
    }
}
