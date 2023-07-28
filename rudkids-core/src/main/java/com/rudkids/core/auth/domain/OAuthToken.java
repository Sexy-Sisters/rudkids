package com.rudkids.core.auth.domain;

import com.rudkids.core.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_oauth_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthToken {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "oauth_token_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "kakao_refresh_token")
    private String kakaoRefreshToken;

    private OAuthToken(User user, String kakaoRefreshToken) {
        this.user = user;
        this.kakaoRefreshToken = kakaoRefreshToken;
    }

    public static OAuthToken create(User user, String kakaoRefreshToken) {
        return new OAuthToken(user, kakaoRefreshToken);
    }

    public void change(String kakaoRefreshToken) {
        if(!Objects.isNull(kakaoRefreshToken)) {
            this.kakaoRefreshToken = kakaoRefreshToken;
        }
    }
}
