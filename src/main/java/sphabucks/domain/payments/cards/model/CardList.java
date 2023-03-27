package sphabucks.domain.payments.cards.model;

import jakarta.persistence.*;
import lombok.*;
import sphabucks.domain.users.model.User;
import sphabucks.global.utility.BaseTimeEntity;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Card card;

    @Column(columnDefinition = "boolean default false")
    private boolean isRepresent;
}
