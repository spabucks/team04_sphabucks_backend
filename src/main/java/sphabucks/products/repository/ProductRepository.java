package sphabucks.products.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sphabucks.products.vo.IResponseSearchResult;
import sphabucks.products.vo.ResponseSearchResult;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public List<ResponseSearchResult> searchProduct() {

        String jpql = "select s from ProductCategoryList s";

        TypedQuery<ResponseSearchResult> query = em.createQuery(jpql, ResponseSearchResult.class)
                .setMaxResults(1000);

        return query.getResultList();
    }

}
