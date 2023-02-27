package sphabucks.shipping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sphabucks.users.model.User;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String recipient;
    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User userId;
}
