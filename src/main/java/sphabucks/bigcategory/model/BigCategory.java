package sphabucks.bigcategory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BigCategory {
    @Id
    private Long id;
    private String name;
}
