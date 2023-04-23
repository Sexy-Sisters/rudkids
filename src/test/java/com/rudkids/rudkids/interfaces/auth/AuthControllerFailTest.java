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
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerFailTest extends ControllerTest {

    @DisplayName("로그인 요청할 때 OAuth통신에 문제가 생기면 상태코드 500을 반환한다.")
    @Test
    void 로그인_요청할_때_OAuth통신에_문제가_생기면_상태코드_500을_반환한다() throws Exception {
        doThrow(new OAuthException())
                .when(authService)
                .generateAccessAndRefreshToken(any());

        mockMvc.perform(post("/api/v1/auth/{oauthProvider}/token", GOOGLE_PROVIDER)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_토큰_요청())))
                .andDo(print())
                .andDo(document("auth/generateAccessAndRefreshToken/failByResourceServerError",
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

    @DisplayName("잘못된 리프래쉬 토큰으로 요청해서 새로운 엑세스 토큰 요청시 상태코드 401을 반환한다")
    @Test
    void 잘못된_리프래쉬_토큰으로_요청해서_새로운_엑세스_토큰_요청시_상태코드_401을_반환한다() throws Exception {
        doThrow(new InvalidTokenException())
                .when(authService)
                .generateRenewalAccessToken(any());

        mockMvc.perform(post("/api/v1/auth/renewal/access")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_엑세스_토큰_재발급_요청())))
                .andDo(print())
                .andDo(document("auth/generateRenewalAccessToken/failByinvalidTokenError",
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

    @DisplayName("만료된 리프래쉬 토큰으로 요청해서 새로운 엑세스 토큰 요청시 상태코드 401을 반환한다")
    @Test
    void 만료된_리프래쉬_토큰으로_요청해서_새로운_엑세스_토큰_요청시_상태코드_401을_반환한다() throws Exception {
        doThrow(new ExpiredTokenException())
                .when(authService)
                .generateRenewalAccessToken(any());

        mockMvc.perform(post("/api/v1/auth/renewal/access")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_엑세스_토큰_재발급_요청())))
                .andDo(print())
                .andDo(document("auth/generateRenewalAccessToken/failByExpireTokenError",
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
