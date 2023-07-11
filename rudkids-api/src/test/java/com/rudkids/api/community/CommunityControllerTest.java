package com.rudkids.api.community;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.community.exception.CommunityNotFoundException;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.api.community.CommunityFixturesAndDocs.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommunityControllerTest extends ControllerTest {

    @Nested
    @DisplayName("커뮤니티글을 작성한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(communityService.create(any(), any()))
                .willReturn(COMMUNITY_ID);

            mockMvc.perform(post(COMMUNITY_DEFAULT_URL)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(COMMUNITY_작성_요청())))
                .andDo(print())
                .andDo(document("community/create",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    requestFields(
                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("제목"),

                        fieldWithPath("content")
                            .type(JsonFieldType.STRING)
                            .description("내용"),

                        fieldWithPath("type")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 타입"),

                        fieldWithPath("image")
                            .type(JsonFieldType.OBJECT)
                            .description("커뮤니티 글 썸네일"),

                        fieldWithPath("image.path")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 path"),

                        fieldWithPath("image.url")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 url")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("커뮤니티글들을 조회한다")
    class getAll {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(communityService.getAll(any(), any()))
                .willReturn(COMMUNITY_전체조회_응답());

            mockMvc.perform(get(COMMUNITY_DEFAULT_URL + "?type={type}", COMMUNITY_타입))
                .andDo(print())
                .andDo(document("community/getAll",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("type")
                            .description("커뮤니티 타입")
                    ),
                    responseFields(
                        fieldWithPath("[].title")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 제목"),

                        fieldWithPath("[].writer")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 작성자"),

                        fieldWithPath("[].image")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일")
                    )
                ))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("커뮤니티글을 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            given(communityService.get(any()))
                .willReturn(COMMUNITY_상세조회_응답());

            mockMvc.perform(get(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID))
                .andDo(print())
                .andDo(document("community/get",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id")
                            .description("커뮤니티 id")
                    ),
                    responseFields(
                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 제목"),

                        fieldWithPath("content")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 내용"),

                        fieldWithPath("writer")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 작성자"),

                        fieldWithPath("image")
                            .type(JsonFieldType.OBJECT)
                            .description("커뮤니티 썸네일"),

                        fieldWithPath("image.path")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 썸네일 path"),

                        fieldWithPath("image.url")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 썸네일 url"),

                        fieldWithPath("writerProfileImage")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 작성자 프로필 사진"),

                        fieldWithPath("likeCount")
                            .type(JsonFieldType.NUMBER)
                            .description("커뮤니티 좋아요 개수")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 커뮤니티글")
        void fail() throws Exception {
            doThrow(new CommunityNotFoundException())
                .when(communityService)
                .get(any());

            mockMvc.perform(get(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID))
                .andDo(print())
                .andDo(document("community/get/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 매거진 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("커뮤니티글 정보를 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(communityService)
                .update(any(), any(), any());

            mockMvc.perform(put(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(COMMUNITY_수정_요청())))
                .andDo(print())
                .andDo(document("community/update",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("커뮤니티 id")
                    ),
                    requestFields(
                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("새로운 제목"),

                        fieldWithPath("content")
                            .type(JsonFieldType.STRING)
                            .description("새로운 내용"),

                        fieldWithPath("image")
                            .type(JsonFieldType.OBJECT)
                            .description("커뮤니티 글 썸네일"),

                        fieldWithPath("image.path")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 path"),

                        fieldWithPath("image.url")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 url")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 다른유저의 커뮤니티글")
        void fail() throws Exception {
            doThrow(new DifferentUserException())
                .when(communityService)
                .update(any(), any(), any());

            mockMvc.perform(put(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(COMMUNITY_수정_요청())))
                .andDo(print())
                .andDo(document("community/update/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("다른 사용자의 JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("매거진 id")
                    ),
                    requestFields(
                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("새로운 제목"),

                        fieldWithPath("content")
                            .type(JsonFieldType.STRING)
                            .description("새로운 내용"),

                        fieldWithPath("image")
                            .type(JsonFieldType.OBJECT)
                            .description("커뮤니티 글 썸네일"),

                        fieldWithPath("image.path")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 path"),

                        fieldWithPath("image.url")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 url")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 커뮤니티글")
        void fail2() throws Exception {
            doThrow(new CommunityNotFoundException())
                .when(communityService)
                .update(any(), any(), any());

            mockMvc.perform(put(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(COMMUNITY_수정_요청())))
                .andDo(print())
                .andDo(document("community/update/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 매거진 id")
                    ),
                    requestFields(
                        fieldWithPath("title")
                            .type(JsonFieldType.STRING)
                            .description("새로운 제목"),

                        fieldWithPath("content")
                            .type(JsonFieldType.STRING)
                            .description("새로운 내용"),

                        fieldWithPath("image")
                            .type(JsonFieldType.OBJECT)
                            .description("커뮤니티 글 썸네일"),

                        fieldWithPath("image.path")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 path"),

                        fieldWithPath("image.url")
                            .type(JsonFieldType.STRING)
                            .description("커뮤니티 글 썸네일 url")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("커뮤니티글 정보를 삭제한다")
    class delete {

        @Test
        @DisplayName("성공")
        void success() throws Exception {
            willDoNothing()
                .given(communityService)
                .delete(any(), any());

            mockMvc.perform(delete(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("community/delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(JWT_ACCESS_TOKEN()),
                    pathParameters(
                        parameterWithName("id")
                            .description("커뮤니티 id")
                    )
                ))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("실패: 다른유저의 커뮤니티글")
        void fail() throws Exception {
            doThrow(new DifferentUserException())
                .when(communityService)
                .delete(any(), any());

            mockMvc.perform(delete(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("community/delete/fail/forbidden",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("어드민 권한이 아닌 사용자의 JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("커뮤니티 id")
                    )
                ))
                .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("실패: 존재하지 않는 커뮤니티글")
        void fail2() throws Exception {
            doThrow(new CommunityNotFoundException())
                .when(communityService)
                .delete(any(), any());

            mockMvc.perform(delete(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("community/delete/fail/notFound",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName("Authorization")
                            .description("JWT Access Token")
                    ),
                    pathParameters(
                        parameterWithName("id")
                            .description("존재하지 않는 매거진 id")
                    )
                ))
                .andExpect(status().isNotFound());
        }
    }
}
