package com.rudkids.rudkids.domain.auth.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.user.domain.User;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "tbl_oauth_tokens")
@Entity
public class OAuthToken {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    protected OAuthToken() {
    }

    public OAuthToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public void checkRefreshToken(String refreshToken) {
        if(refreshToken == null || refreshToken.isBlank()) {
            this.refreshToken = refreshToken;
        }
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
