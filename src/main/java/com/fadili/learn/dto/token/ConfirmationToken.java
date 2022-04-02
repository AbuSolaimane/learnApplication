package com.fadili.learn.dto.token;

import com.fadili.learn.models.UserApp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private UserApp userApp;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
                                    LocalDateTime confirmedAt, UserApp userApp) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.confirmedAt = confirmedAt;
        this.userApp = userApp;
    }
}
