package sphabucks.domain.payments.gifticons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.domain.users.model.User;
import sphabucks.global.utility.BaseTimeEntity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftIconList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private GiftIcon giftIcon;
}
