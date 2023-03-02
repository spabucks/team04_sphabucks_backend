package sphabucks.users.model;

import jakarta.persistence.*;
import lombok.*;
import sphabucks.utility.BaseTimeEntity;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {

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

    private String nickname;
    private String phoneNum;
    private String address;
    private Integer star;
    private String carNum;
    private Date birth;
    private boolean sex;    // {true: M, false: F}
    private String grade;

}
