package sphabucks.products.service;

import sphabucks.products.model.BigCategory;
import sphabucks.products.model.Category;

import java.util.List;

public interface IBigCategoryService {

    void addBigCategory(BigCategory bigCategory);
    BigCategory getBigCategory(Integer bigCategoryId);
    List<BigCategory> getAll();
}
