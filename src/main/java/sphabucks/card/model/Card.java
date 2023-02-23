package sphabucks.card.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Card {
    @Id
    private Long id;
    private String type;
    private String number;
    private boolean isUsed;
    private Date date;
}
