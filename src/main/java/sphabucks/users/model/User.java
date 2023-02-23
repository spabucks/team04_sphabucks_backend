package sphabucks.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pwd;
    @Column(nullable = true)
    private String nickname;
}
