package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.user.UserControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest {

    @DisplayName("[유저-수정]")
    @Test
    void 유저_정보를_수정한다() throws Exception {
        willDoNothing()
            .given(userService)
            .update(any(), any());

        mockMvc.perform(put(USER_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(USER_수정_요청())))
            .andDo(print())
            .andDo(document("user/update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("유저 이름"),

                    fieldWithPath("phoneNumber")
                        .type(JsonFieldType.STRING)
                        .description("유저 폰번호"),

                    fieldWithPath("profileImagePath")
                        .type(JsonFieldType.STRING)
                        .description("유저 이미지 주소"),

                    fieldWithPath("profileImageUrl")
                        .type(JsonFieldType.STRING)
                        .description("유저 이미지 url")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[유저-조회]")
    @Test
    void 유저_정보를_조회한다() throws Exception {
        given(userService.find(any()))
            .willReturn(USER_정보_조회());

        mockMvc.perform(get(USER_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("user/find",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                responseFields(
                    fieldWithPath("email")
                        .type(JsonFieldType.STRING)
                        .description("이메일"),

                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("이름"),

                    fieldWithPath("gender")
                        .type(JsonFieldType.STRING)
                        .description("성별"),

                    fieldWithPath("age")
                        .type(JsonFieldType.NUMBER)
                        .description("나이"),

                    fieldWithPath("phoneNumber")
                        .type(JsonFieldType.STRING)
                        .description("폰번호"),

                    fieldWithPath("profileImage")
                        .type(JsonFieldType.STRING)
                        .description("프로필 이미지")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[유저-주소-조회]")
    @Test
    void 유저_주소_정보들을_조회한다() throws Exception {
        given(userService.findAddresses(any()))
            .willReturn(USER_주소_정보들_조회());

        mockMvc.perform(get(USER_DEFAULT_URL + "/address")
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("user/findAddress",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                responseFields(
                    fieldWithPath("[]receiverName")
                        .type(JsonFieldType.STRING)
                        .description("받는사람 이름"),

                    fieldWithPath("[]receiverPhone")
                        .type(JsonFieldType.STRING)
                        .description("받는사람 폰번호"),

                    fieldWithPath("[]receiverAddress1")
                        .type(JsonFieldType.STRING)
                        .description("받는사람 주소"),

                    fieldWithPath("[]receiverAddress2")
                        .type(JsonFieldType.STRING)
                        .description("받는사람 상세주소"),

                    fieldWithPath("[]receiverZipCode")
                        .type(JsonFieldType.STRING)
                        .description("받는사람 zipcode"),

                    fieldWithPath("[]message")
                        .type(JsonFieldType.STRING)
                        .description("etc 메세지")
                )
            ))
            .andExpect(status().isOk());
    }
}