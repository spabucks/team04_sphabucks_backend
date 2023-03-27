package sphabucks.domain.products.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sphabucks.domain.products.model.ProductSearch;
import sphabucks.domain.products.vo.RequestSearchParam;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public List<ProductSearch> searchProduct(RequestSearchParam requestSearchParam, Long page) {

        String jpql = "select p from ProductSearch p";
        Boolean isFirst = true;
        if (requestSearchParam.getKeyword() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.product.name LIKE :keyword";
        }
        if (requestSearchParam.getBigCategory() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            if (requestSearchParam.getBigCategory() == 0) {
                jpql += " 1 = 1";
            } else {
                jpql += " p.bigCategory.id = :bigCategory";
            }

        }
        if (requestSearchParam.getSize() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.product.size in :size";
        }
//        if (requestSearchParam.getPrice() != null) {
//            if (isFirst) {
//                jpql += " where";
//                isFirst = false;
//            } else {
//                jpql += " and";
//            }
//            jpql += " (p.product.price between :price and :price2)";
//        }
        if (requestSearchParam.getPrice() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " (";
            for (int i = 0; i < requestSearchParam.getPrice().size(); i++) {
                if (i != 0) {
                    jpql += " or";
                }
                jpql += " (p.product.price between :price" + i + " and :price" + i + "plus10000)";
            }
            jpql += " )";
        }

        // sorting 추가
        if (requestSearchParam.getSorting() != null) {
            if (requestSearchParam.getSorting() == 1L) { // 좋아요순
                jpql += " order by p.product.likeCount desc";
            } else if(requestSearchParam.getSorting() == 2L) { // 낮은가격순
                jpql += " order by p.product.price asc";
            } else if(requestSearchParam.getSorting() == 3L) { // 높은가격순
                jpql += " order by p.product.price desc";
            }
        }

        if (requestSearchParam.getSmallCategory() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.smallCategory.id in :smallCategory";
        }
        if (requestSearchParam.getSeason() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.event.id in :event";
        }



        TypedQuery<ProductSearch> query = em.createQuery(jpql, ProductSearch.class)
                .setMaxResults(10);

        if(page != null) {
            query.setFirstResult(page.intValue()*10);
        }

        if (requestSearchParam.getKeyword() != null) {
            query.setParameter("keyword", "%"+requestSearchParam.getKeyword()+"%");
        }
        if (requestSearchParam.getBigCategory() != null && requestSearchParam.getBigCategory() != 0) {
            query.setParameter("bigCategory", requestSearchParam.getBigCategory());
        }
        if (requestSearchParam.getSize() != null) {
            query.setParameter("size", requestSearchParam.getSize());
        }
        if (requestSearchParam.getPrice() != null) {
            for (int i = 0; i < requestSearchParam.getPrice().size(); i++) {
                query.setParameter("price" + i, requestSearchParam.getPrice().get(i));
                query.setParameter("price" + i + "plus10000", requestSearchParam.getPrice().get(i) + 9999);
            }
        }
        if (requestSearchParam.getSmallCategory() != null) {
            query.setParameter("smallCategory", requestSearchParam.getSmallCategory());
        }
        if (requestSearchParam.getSeason() != null) {
            query.setParameter("event", requestSearchParam.getSeason());
        }

        return query.getResultList();
    }

}
