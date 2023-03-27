package com.rudkids.rudkids.interfaces.auth;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.auth.exception.InvalidTokenException;
import com.rudkids.rudkids.infrastructure.oauth.exception.OAuthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.AuthFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends ControllerTest {

    @DisplayName("로그인창 링크를 생성하고 반환한다.")
    @Test
    void 로그인창_링크를_생성하고_반환한다() throws Exception {
        given(oAuthUri.generate(any())).willReturn(OAuth_로그인_링크);

        mockMvc.perform(get(
                "/api/auth/{oauthProvider}/oauth-uri?redirectUri={redirectUri}",
                        GOOGLE_PROVIDER,
                        REDIRECT_URI))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("로그인 요청하면 토큰 발급")
    @Test
    void 로그인_요청하면_토큰_발급() throws Exception {
        given(authService.generateAccessAndRefreshToken(any()))
                .willReturn(MEMBER_토큰_응답());

        mockMvc.perform(post("/api/auth/{oauthProvider}/token", GOOGLE_PROVIDER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MEMBER_토큰_요청())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("로그인 요청할 때 OAuth통신에 문제가 생기면 상태코드 500 반환")
    @Test
    void 로그인_요청할_때_OAuth통신에_문제가_생기면_상태코드_500_반환() throws Exception {
        doThrow(new OAuthException())
                .when(authService)
                .generateAccessAndRefreshToken(any());

        mockMvc.perform(post("/api/auth/{oauthProvider}/token", GOOGLE_PROVIDER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MEMBER_토큰_요청())))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @DisplayName("리프래쉬 토큰을 요청해서 새로운 에세스 토큰 요청 발급")
    @Test
    void 리프래쉬_토큰을_요청해서_새로운_에세스_토큰_요청_발급() throws Exception {
        given(authService.generateRenewalAccessToken(any()))
                .willReturn(에세스_토큰_재발급_응답());

        mockMvc.perform(post("/api/auth/renewal/access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MEMBER_에세스_토큰_재발급_요청())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("잘못된 리프래쉬 토큰으로 요청해서 새로운 에세스 토큰 요청시 상태코드 401 반환")
    @Test
    void 잘못된_리프래쉬_토큰으로_요청해서_새로운_에세스_토큰_요청시_상태코드_401_반환() throws Exception {
        doThrow(new InvalidTokenException())
                .when(authService)
                .generateRenewalAccessToken(any());

        mockMvc.perform(post("/api/auth/renewal/access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MEMBER_에세스_토큰_재발급_요청())))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}