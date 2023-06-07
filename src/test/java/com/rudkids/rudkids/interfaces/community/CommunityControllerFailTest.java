package com.rudkids.rudkids.interfaces.community;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.community.exception.CommunityNotFoundException;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.community.CommunityControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommunityControllerFailTest extends ControllerTest {

    @DisplayName("[커뮤니티-상세조회-404-에러]")
    @Test
    void 존재하지_않는_커뮤니티를_상세조회할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new CommunityNotFoundException())
            .when(communityService)
            .find(any());

        mockMvc.perform(get(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID))
            .andDo(print())
            .andDo(document("community/find/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 매거진 id")
                )
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[커뮤니티-수정-403-에러]")
    @Test
    void 다른_사용자의_글을_수정할_경우_상태코드_403를_반환한다() throws Exception {
        doThrow(new DifferentUserException())
            .when(communityService)
            .update(any(), any(), any());

        mockMvc.perform(put(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(COMMUNITY_수정_요청())))
            .andDo(print())
            .andDo(document("community/update/failForbiddenError",
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

    @DisplayName("[커뮤니티-수정-404-에러]")
    @Test
    void 존재하지_않는_커뮤니티를_수정할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new CommunityNotFoundException())
            .when(communityService)
            .update(any(), any(), any());

        mockMvc.perform(put(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(COMMUNITY_수정_요청())))
            .andDo(print())
            .andDo(document("community/update/failByNotFoundError",
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

    @DisplayName("[커뮤니티-삭제-403-에러]")
    @Test
    void 다른유저의_커뮤니티를_삭제할_경우_상태코드_403를_반환한다() throws Exception {
        doThrow(new DifferentUserException())
            .when(communityService)
            .delete(any(), any());

        mockMvc.perform(delete(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("community/delete/failByForbiddenError",
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

    @DisplayName("[커뮤니티-삭제-404-에러]")
    @Test
    void 존재하지_않는_커뮤니티를_삭제할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new CommunityNotFoundException())
            .when(communityService)
            .delete(any(), any());

        mockMvc.perform(delete(COMMUNITY_DEFAULT_URL + "/{id}", COMMUNITY_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("community/delete/failByNotFoundError",
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