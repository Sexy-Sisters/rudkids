package com.rudkids.api.user;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.user.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.common.fixtures.user.UserFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
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

    @Nested
    @DisplayName("유저정보를 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
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

        @Test
        @DisplayName("실패: 잘못된 이름")
        void fail() throws Exception {
            doThrow(new InvalidNameException())
                .when(userService)
                .update(any(), any());

            mockMvc.perform(put(USER_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(USER_수정_요청())))
                .andDo(print())
                .andDo(document("user/update/fail/badRequest",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    requestFields(
                        fieldWithPath("name")
                            .type(JsonFieldType.STRING)
                            .description("잘못된 유저 이름"),

                        fieldWithPath("profileImagePath")
                            .type(JsonFieldType.STRING)
                            .description("유저 이미지 주소"),

                        fieldWithPath("profileImageUrl")
                            .type(JsonFieldType.STRING)
                            .description("유저 이미지 url")
                    )
                ))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("유저정보를 조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(userService.get(any()))
                .willReturn(USER_정보_조회());

            mockMvc.perform(get(USER_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("user/get",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    responseFields(
                        fieldWithPath("userId")
                            .type(JsonFieldType.STRING)
                            .description("유저 id"),

                        fieldWithPath("email")
                            .type(JsonFieldType.STRING)
                            .description("이메일"),

                        fieldWithPath("name")
                            .type(JsonFieldType.STRING)
                            .description("이름"),

                        fieldWithPath("phoneNumber")
                            .type(JsonFieldType.STRING)
                            .description("폰번호"),

                        fieldWithPath("profileImage")
                            .type(JsonFieldType.OBJECT)
                            .description("프로필 이미지"),

                        fieldWithPath("profileImage.path")
                            .type(JsonFieldType.STRING)
                            .description("프로필 이미지 path"),

                        fieldWithPath("profileImage.url")
                            .type(JsonFieldType.STRING)
                            .description("프로필 이미지 url")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("유저가 작성한 커뮤니티 글들을 조회한다")
    class getMyCommunities {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(userService.getMyCommunities(any()))
                .willReturn(USER_커뮤니티글_응답());

            mockMvc.perform(get(USER_DEFAULT_URL + "/community")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("user/getMyCommunities",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    responseFields(
                        fieldWithPath("[]title")
                            .type(JsonFieldType.STRING)
                            .description("제목"),

                        fieldWithPath("[]writer")
                            .type(JsonFieldType.STRING)
                            .description("작성자"),

                        fieldWithPath("[]image")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 썸네일")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("유저 주소정보들을 조회한다")
    class getAddresses {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(userService.getAddresses(any()))
                .willReturn(USER_주소_정보들_조회());

            mockMvc.perform(get(USER_DEFAULT_URL + "/address")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("user/getAddresses",
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
}