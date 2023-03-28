package sphabucks.domain.products.service;

import sphabucks.domain.products.model.BigCategory;
import sphabucks.domain.products.vo.RequestBigCategory;

import java.util.List;

public interface IBigCategoryService {

    void addBigCategory(RequestBigCategory requestBigCategory);
    BigCategory getBigCategory(Long bigCategoryId);
    List<BigCategory> getAll();
}
