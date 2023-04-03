package sphabucks.global.auth.vo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestFindId {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userName;
}
