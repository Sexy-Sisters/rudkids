package com.rudkids.api.auth;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.auth.exception.ExpiredTokenException;
import com.rudkids.core.auth.exception.InvalidTokenException;
import com.rudkids.core.auth.exception.OAuthException;
import com.rudkids.core.auth.exception.OAuthNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.auth.AuthFixturesAndDocs.*;
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

    @Nested
    @DisplayName("로그인 링크를 생성하고 반환한다")
    class generateOAuthUri {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(oAuthUri.generate(any(), any()))
                .willReturn(OAuth_로그인_링크);

            mockMvc.perform(get("/api/v1/auth/{oauthProvider}/oauth-uri?redirectUri={redirectUri}",
                    GOOGLE_PROVIDER,
                    REDIRECT_URI))
                .andDo(print())
                .andDo(document("auth/generateLink",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("oauthProvider")
                            .description("OAuth 로그인 제공자 (google, kakao)")
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

        @Test
        @DisplayName("실패: 존재하지 않는 OAuth 제공자")
        void fail() throws Exception {
            doThrow(new OAuthNotFoundException())
                .when(oAuthUri)
                .generate(any(), any());

            mockMvc.perform(get("/api/v1/auth/{oauthProvider}/oauth-uri?redirectUri={redirectUri}",
                    KAKAO_PROVIDER,
                    REDIRECT_URI))
                .andDo(print())
                .andDo(document("auth/generateLink/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("oauthProvider")
                            .description("OAuth 로그인 제공자")
                    ),
                    queryParameters(
                        parameterWithName("redirectUri")
                            .description("OAuth Redirect URI")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("로그인을 요청하면 토큰을 발급한다")
    class generateAccessAndRefreshToken {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
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
                            .description("루키즈 Refresh Token"),

                        fieldWithPath("hasPhoneNumber")
                            .type(JsonFieldType.BOOLEAN)
                            .description("루키즈 폰번호 인증 여부")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 외부 API 통신")
        void fail() throws Exception {
            doThrow(new OAuthException())
                .when(authService)
                .generateAccessAndRefreshToken(any());

            mockMvc.perform(post("/api/v1/auth/{oauthProvider}/token", GOOGLE_PROVIDER)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(USER_토큰_요청())))
                .andDo(print())
                .andDo(document("auth/generateAccessAndRefreshToken/fail/internal",
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
                    )
                ))
                .andExpect(status().isInternalServerError());
        }
    }

    @Nested
    @DisplayName("리프래쉬 토큰을 요청해서 새로운 엑세스 토큰을 발급한다")
    class generateRenewalAccessToken {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
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

        @Test
        @DisplayName("실패: 잘못된 리프래쉬 토큰")
        void fail() throws Exception {
            doThrow(new InvalidTokenException())
                .when(authService)
                .generateRenewalAccessToken(any());

            mockMvc.perform(post("/api/v1/auth/renewal/access")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(USER_엑세스_토큰_재발급_요청())))
                .andDo(print())
                .andDo(document("auth/generateRenewalAccessToken/fail/invalidToken",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("refreshToken")
                            .type(JsonFieldType.STRING)
                            .description("잘못된 리프래쉬 토큰")
                    )
                ))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("실패: 만료된 리프래쉬 토큰")
        void fail2() throws Exception {
            doThrow(new ExpiredTokenException())
                .when(authService)
                .generateRenewalAccessToken(any());

            mockMvc.perform(post("/api/v1/auth/renewal/access")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(USER_엑세스_토큰_재발급_요청())))
                .andDo(print())
                .andDo(document("auth/generateRenewalAccessToken/fail/expireToken",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("refreshToken")
                            .type(JsonFieldType.STRING)
                            .description("만료된 리프래쉬 토큰")
                    )
                ))
                .andExpect(status().isUnauthorized());
        }
    }
}