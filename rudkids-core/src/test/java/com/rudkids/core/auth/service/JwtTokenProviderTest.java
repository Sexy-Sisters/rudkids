package com.rudkids.core.auth.service;

import com.rudkids.core.common.fixtures.JwtTokenProviderFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class JwtTokenProviderTest extends JwtTokenProviderFixtures {

    @DisplayName("[Auth-엑세스토큰생성]")
    @Test
    void 엑세스_토큰_생성() {
        String accessToken = jwtTokenProvider.createAccessToken(JwtTokenProviderFixtures.PAYLOAD);

        assertThat(accessToken.split("\\.")).hasSize(3);
    }

    @DisplayName("[Auth-리프래쉬토큰생성]")
    @Test
    void 리프래쉬_토큰_생성() {
        String refreshToken = jwtTokenProvider.createRefreshToken(JwtTokenProviderFixtures.PAYLOAD);

        assertThat(refreshToken.split("\\.")).hasSize(3);
    }

    @DisplayName("[Auth-페이로드추출]")
    @Test
    void 페이로드를_가져온다() {
        String accessToken = jwtTokenProvider.createAccessToken(JwtTokenProviderFixtures.PAYLOAD);

        String payload = jwtTokenProvider.getPayload(accessToken);

        assertThat(payload).isEqualTo(JwtTokenProviderFixtures.PAYLOAD);
    }
}