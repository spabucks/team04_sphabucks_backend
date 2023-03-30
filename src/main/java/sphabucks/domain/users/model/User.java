package sphabucks.domain.users.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sphabucks.global.utility.BaseTimeEntity;

import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // index

    @Column(nullable = false)
    private String loginId; // 로그인할 때 사용하는 id
    @Column(nullable = false)
    private String userId; // uuid 값
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pwd;

    private String nickname;
    private String phoneNum;
    private String address;
    private Long star;
    private String carNum;
    private Date birth;
    private boolean sex;    // {true: M, false: F}
    private String grade;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
