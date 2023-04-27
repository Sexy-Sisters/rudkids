package com.rudkids.rudkids.domain.auth.service;

import com.rudkids.rudkids.common.fixtures.auth.JwtTokenProviderFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class JwtTokenProviderTest extends JwtTokenProviderFixtures {

    @DisplayName("[Auth-엑세스토큰생성]")
    @Test
    void 엑세스_토큰_생성() {
        String accessToken = jwtTokenProvider.createAccessToken(PAYLOAD);

        assertThat(accessToken.split("\\.")).hasSize(3);
    }

    @DisplayName("[Auth-리프래쉬토큰생성]")
    @Test
    void 리프래쉬_토큰_생성() {
        String refreshToken = jwtTokenProvider.createRefreshToken(PAYLOAD);

        assertThat(refreshToken.split("\\.")).hasSize(3);
    }

    @DisplayName("[Auth-페이로드추출]")
    @Test
    void 페이로드를_가져온다() {
        String accessToken = jwtTokenProvider.createAccessToken(PAYLOAD);

        String payload = jwtTokenProvider.getPayload(accessToken);

        assertThat(payload).isEqualTo(PAYLOAD);
    }
}