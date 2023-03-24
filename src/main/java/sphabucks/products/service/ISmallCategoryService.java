package sphabucks.products.service;

import sphabucks.products.model.BigCategory;
import sphabucks.products.model.SmallCategory;
import sphabucks.products.vo.RequestSmallCategory;

import java.util.List;

public interface ISmallCategoryService {

    void addSmallCategory(RequestSmallCategory requestSmallCategory);
    SmallCategory getSmallCategory(Long smallCategoryId);
    List<SmallCategory> getAll();
    List<SmallCategory> getAllByType(BigCategory bigCategory);
}
