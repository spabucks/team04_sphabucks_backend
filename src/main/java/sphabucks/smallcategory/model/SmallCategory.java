package sphabucks.smallcategory.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SmallCategory {
    @Id
    private Long id;

}
