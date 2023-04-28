package com.rudkids.rudkids.interfaces.auth;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.auth.exception.ExpiredTokenException;
import com.rudkids.rudkids.domain.auth.exception.InvalidTokenException;
import com.rudkids.rudkids.infrastructure.oauth.exception.OAuthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.auth.AuthControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends ControllerTest {

    @DisplayName("[Auth-로그인창생성]")
    @Test
    void 로그인창_링크를_생성하고_반환한다() throws Exception {
        given(oAuthUri.generate(any(), any())).willReturn(OAuth_로그인_링크);

        mockMvc.perform(get(
                "/api/v1/auth/{oauthProvider}/oauth-uri?redirectUri={redirectUri}",
                GOOGLE_PROVIDER,
                REDIRECT_URI))
            .andDo(print())
            .andDo(document("auth/generateLink",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("oauthProvider")
                        .description("OAuth 로그인 제공자")
                ),
                queryParameters(
                    parameterWithName("redirectUri")
                        .description("OAuth Redirect URI")
                ),
                responseFields(
                    fieldWithPath("oAuthUri")
                        .type(JsonFieldType.STRING)
                        .description("OAuth 소셜 로그인 링크")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[Auth-로그인]")
    @Test
    void 로그인을_요청하면_토큰을_발급한다() throws Exception {
        given(authService.generateAccessAndRefreshToken(any()))
            .willReturn(USER_토큰_응답());

        mockMvc.perform(post("/api/v1/auth/{oauthProvider}/token", GOOGLE_PROVIDER)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(USER_토큰_요청())))
            .andDo(print())
            .andDo(document("auth/generateAccessAndRefreshToken",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("oauthProvider")
                        .description("OAuth 로그인 제공자")
                ),
                requestFields(
                    fieldWithPath("authorizationCode")
                        .type(JsonFieldType.STRING)
                        .description("OAuth 로그인 인증 코드"),

                    fieldWithPath("redirectUri")
                        .type(JsonFieldType.STRING)
                        .description("OAuth Redirect URI")
                ),
                responseFields(
                    fieldWithPath("accessToken")
                        .type(JsonFieldType.STRING)
                        .description("루키즈 Access Token"),

                    fieldWithPath("refreshToken")
                        .type(JsonFieldType.STRING)
                        .description("루키즈 Refresh Token")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[Auth-엑세스토큰재발급]")
    @Test
    void 리프래쉬_토큰을_요청해서_새로운_엑세스_토큰을_발급한다() throws Exception {
        given(authService.generateRenewalAccessToken(any()))
            .willReturn(엑세스_토큰_재발급_응답());

        mockMvc.perform(post("/api/v1/auth/renewal/access")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(USER_엑세스_토큰_재발급_요청())))
            .andDo(print())
            .andDo(document("auth/generateRenewalAccessToken",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("refreshToken")
                        .type(JsonFieldType.STRING)
                        .description("리프래쉬 토큰")
                ),
                responseFields(
                    fieldWithPath("accessToken")
                        .type(JsonFieldType.STRING)
                        .description("루키즈 새로운 Access Token")
                )
            ))
            .andExpect(status().isOk());
    }
}