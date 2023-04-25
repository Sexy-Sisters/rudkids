package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.user.exception.InvalidAgeRangeException;
import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.USER_잘못된_유저_성별_정보_수정_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerFailTest extends ControllerTest {

    @DisplayName("잘못된 나이를 입력하고 유저정보를 등록하면 상태코드 400을 반환한다.")
    @Test
    void 잘못된_나이를_입력하고_유저정보를_등록하면_상태코드_400을_반환한다() throws Exception {
        doThrow(new InvalidAgeRangeException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/v1/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_나이_회원가입_요청())))
                .andDo(print())
                .andDo(document("user/sign-up/failByInvalidAgeRangeError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("age")
                                        .type(JsonFieldType.NUMBER)
                                        .description("잘못된 유저 나이"),

                                fieldWithPath("gender")
                                        .type(JsonFieldType.STRING)
                                        .description("유저 성별")
                        )
                ))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("잘못된 성별을 입력하고 유저정보를 등록하면 상태코드 400을 반환한다.")
    @Test
    void 잘못된_성별을_입력하고_유저정보를_등록하면_상태코드_400을_반환한다() throws Exception {
        doThrow(new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(post("/api/v1/user/sign-up")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_성별_회원가입_요청())))
                .andDo(print())
                .andDo(document("user/sign-up/failByInvalidGenderError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("age")
                                        .type(JsonFieldType.NUMBER)
                                        .description("유저 나이"),

                                fieldWithPath("gender")
                                        .type(JsonFieldType.STRING)
                                        .description("잘못된 유저 성별")
                        )
                ))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("잘못된 나이를 입력하고 유저정보를 수정하면 상태코드 400을 반환한다")
    @Test
    void 잘못된_나이를_입력하고_유저정보를_수정하면_상태코드_400을_반환한다() throws Exception {
        doThrow(new InvalidAgeRangeException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/v1/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_유저_나이_정보_수정_요청())))
                .andDo(print())
                .andDo(document("user/update/failByInvalidAgeRangeError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("age")
                                        .type(JsonFieldType.NUMBER)
                                        .description("잘못된 유저 나이"),

                                fieldWithPath("gender")
                                        .type(JsonFieldType.STRING)
                                        .description("유저 성별")
                        )
                ))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("잘못된 성별을 입력하고 유저정보를 수정하면 상태코드 400을 반환한다")
    @Test
    void 잘못된_성별을_입력하고_유저정보를_수정하면_상태코드_400을_반환한다() throws Exception {
        doThrow(new InvalidGenderException())
                .when(userService)
                .update(any(), any());

        mockMvc.perform(put("/api/v1/user/update")
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_잘못된_유저_성별_정보_수정_요청())))
                .andDo(print())
                .andDo(document("user/update/failByInvalidGenderError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        requestFields(
                                fieldWithPath("age")
                                        .type(JsonFieldType.NUMBER)
                                        .description("유저 나이"),

                                fieldWithPath("gender")
                                        .type(JsonFieldType.STRING)
                                        .description("잘못된 유저 성별")
                        )
                ))
                .andExpect(status().isBadRequest());
    }
}
