package sphabucks.products.service;

import sphabucks.products.model.Category;

import java.util.List;

public interface ICategoryService {

    void addCategory(Category category);
    Category getCategory(Integer categoryId);
    List<Category> getAll();
    List<Category> getAllByType(String categoryType);
}
