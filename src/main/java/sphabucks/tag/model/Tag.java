package sphabucks.tag.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Tag {
    @Id
    private Long id;
    private String name;
    private Date date;
}
