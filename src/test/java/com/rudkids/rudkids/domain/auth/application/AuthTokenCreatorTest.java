package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.auth.domain.AuthToken;
import com.rudkids.rudkids.domain.auth.repository.TokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ServiceTest
class AuthTokenCreatorTest {

    @Autowired
    private TokenCreator tokenCreator;

    @Autowired
    private TokenRepository tokenRepository;

    @DisplayName("유저 id로 에세스 토큰과 리프래쉬토큰을 생성한다.")
    @Test
    void 유저_id로_에세스_토큰과_래프래쉬_토큰을_생성한다() {
        UUID userId = UUID.randomUUID();

        AuthToken authToken = tokenCreator.createAuthToken(userId);

        assertAll(() -> {
            assertThat(authToken.getAccessToken()).isNotEmpty();
            assertThat(authToken.getRefreshToken()).isNotEmpty();
        });
    }

    @DisplayName("이미 가입한 회원이라면 같은 리프래쉬 토큰 발급")
    @Test
    void 이미_가입한_회원이라면_같은_리프래쉬_토큰_발급() {
        UUID userId = UUID.randomUUID();
        String refreshToken = "refresh";
        tokenRepository.save(userId, refreshToken);

        AuthToken authToken = tokenCreator.createAuthToken(userId);

        assertThat(authToken.getRefreshToken()).isEqualTo("refresh");
    }

    @DisplayName("리프래쉬 토큰으로 에세스 토큰 재발급")
    @Test
    void 래프래쉬_토큰으로_에세스_토큰_재발급() {
        UUID userId = UUID.randomUUID();
        AuthToken saveToken = tokenCreator.createAuthToken(userId);

        AuthToken authToken = tokenCreator.renewAuthToken(saveToken.getRefreshToken());

        assertAll(() -> {
            assertThat(authToken.getAccessToken()).isNotEmpty();
            assertThat(authToken.getRefreshToken()).isNotEmpty();
        });
    }

    @DisplayName("토큰에서 페이로드 추출")
    @Test
    void 토큰에서_페이로드_추출() {
        UUID userId = UUID.randomUUID();
        AuthToken authToken = tokenCreator.createAuthToken(userId);

        UUID actual = tokenCreator.extractPayload(authToken.getAccessToken());

        assertThat(actual).isEqualTo(userId);
    }
}