package sphabucks.products.service;

import sphabucks.products.model.Category;
import sphabucks.products.vo.RequestCategory;

import java.util.List;

public interface ICategoryService {

    void addCategory(RequestCategory requestCategory);
    Category getCategory(Integer categoryId);
    List<Category> getAll();
    List<Category> getAllByType(String categoryType);
}
