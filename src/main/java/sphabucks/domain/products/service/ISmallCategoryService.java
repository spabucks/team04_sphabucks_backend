package sphabucks.domain.products.service;

import sphabucks.domain.products.vo.RequestSmallCategory;
import sphabucks.domain.products.model.BigCategory;
import sphabucks.domain.products.model.SmallCategory;

import java.util.List;

public interface ISmallCategoryService {

    void addSmallCategory(RequestSmallCategory requestSmallCategory);
    SmallCategory getSmallCategory(Long smallCategoryId);
    List<SmallCategory> getAll();
    List<SmallCategory> getAllByType(BigCategory bigCategory);
}
