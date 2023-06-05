package com.rudkids.rudkids.interfaces.community;

import com.rudkids.rudkids.common.ControllerTest;
import kotlinx.serialization.json.Json;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.community.CommunityControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
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

    @DisplayName("[커뮤니티-생성]")
    @Test
    void 커뮤니티를_작성한다() throws Exception {
        given(communityService.create(any(), any())).willReturn(COMMUNITY_ID);

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
                        .description("커뮤니티 타입")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[커뮤니티-전체조회]")
    @Test
    void 커뮤니티를_전체조회한다() throws Exception {
        given(communityService.findAll(any())).willReturn(COMMUNITY_전체조회_응답());

        mockMvc.perform(get(COMMUNITY_DEFAULT_URL + "?type={type}", COMMUNITY_타입))
            .andDo(print())
            .andDo(document("community/findAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                queryParameters(
                    parameterWithName("type")
                        .description("커뮤니티 타입")
                ),
                responseFields(
                    fieldWithPath("[].title")
                        .type(JsonFieldType.STRING)
                        .description("매거진 제목"),

                    fieldWithPath("[].writer")
                        .type(JsonFieldType.STRING)
                        .description("매거진 작성자")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[커뮤니티-상세조회]")
    @Test
    void 커뮤니티를_상세조회한다() throws Exception {
        given(communityService.find(any())).willReturn(COMMUNITY_상세조회_응답());

        mockMvc.perform(get(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID))
            .andDo(print())
            .andDo(document("community/find",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("커뮤니티 id")
                ),
                responseFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("매거진 제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("매거진 내용"),

                    fieldWithPath("writer")
                        .type(JsonFieldType.STRING)
                        .description("매거진 작성자"),

                    fieldWithPath("writerProfileImage")
                        .type(JsonFieldType.STRING)
                        .description("매거진 작성자 프로필 사진")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[커뮤니티-수정]")
    @Test
    void 커뮤니티를_수정한다() throws Exception {
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
                        .description("매거진 id")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("새로운 제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("새로운 내용")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[커뮤니티-삭제]")
    @Test
    void 커뮤니티를_삭제한다() throws Exception {
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
                        .description("매거진 id")
                )
            ))
            .andExpect(status().isOk());
    }
}