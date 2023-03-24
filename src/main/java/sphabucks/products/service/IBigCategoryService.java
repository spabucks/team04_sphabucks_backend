package sphabucks.products.service;

import sphabucks.products.model.BigCategory;
import sphabucks.products.vo.RequestBigCategory;

import java.util.List;

public interface IBigCategoryService {

    void addBigCategory(RequestBigCategory requestBigCategory);
    BigCategory getBigCategory(Long bigCategoryId);
    List<BigCategory> getAll();
}
