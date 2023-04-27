package com.rudkids.rudkids.domain.auth.service;

import com.rudkids.rudkids.common.fixtures.auth.AuthTokenCreatorFixtures;
import com.rudkids.rudkids.domain.auth.domain.AuthToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AuthTokenCreatorTest extends AuthTokenCreatorFixtures {

    @DisplayName("[Auth-토큰생성]")
    @Test
    void 유저_id로_엑세스_토큰과_래프래쉬_토큰을_생성한다() {
        UUID userId = UUID.randomUUID();

        AuthToken authToken = tokenCreator.createAuthToken(userId);

        assertAll(() -> {
            assertThat(authToken.getAccessToken()).isNotEmpty();
            assertThat(authToken.getRefreshToken()).isNotEmpty();
        });
    }

    @DisplayName("[Auth-리프래쉬토큰발급]")
    @Test
    void 이미_가입한_회원이라면_같은_리프래쉬_토큰_발급() {
        UUID userId = UUID.randomUUID();
        String refreshToken = "refresh";
        tokenRepository.save(userId, refreshToken);

        AuthToken authToken = tokenCreator.createAuthToken(userId);

        assertThat(authToken.getRefreshToken()).isEqualTo("refresh");
    }

    @DisplayName("[Auth-엑세스토큰재발급]")
    @Test
    void 래프래쉬_토큰으로_엑세스_토큰_재발급() {
        UUID userId = UUID.randomUUID();
        AuthToken saveToken = tokenCreator.createAuthToken(userId);

        AuthToken authToken = tokenCreator.renewAuthToken(saveToken.getRefreshToken());

        assertAll(() -> {
            assertThat(authToken.getAccessToken()).isNotEmpty();
            assertThat(authToken.getRefreshToken()).isNotEmpty();
        });
    }

    @DisplayName("[Auth-페이로드추출]")
    @Test
    void 토큰에서_페이로드_추출() {
        UUID userId = UUID.randomUUID();
        AuthToken authToken = tokenCreator.createAuthToken(userId);

        UUID actual = tokenCreator.extractPayload(authToken.getAccessToken());

        assertThat(actual).isEqualTo(userId);
    }
}